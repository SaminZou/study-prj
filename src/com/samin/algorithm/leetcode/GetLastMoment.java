package com.samin.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Iterator;

public class GetLastMoment {

    private static class Obj {
        Integer index;
        Integer des; // 0 向左(-1) , 1 向右(+1)
        boolean hasChange = false;

        public Obj(Integer index, Integer des) {
            this.index = index;
            this.des = des;
        }
    }

    public int getLastMoment(int n, int[] left, int[] right) {
        ArrayList<Obj> objList = new ArrayList<>();

        // 初始化
        for (Integer ele : left) {
            objList.add(new Obj(ele, 0));
        }
        for (Integer ele : right) {
            objList.add(new Obj(ele, 1));
        }

        // 　计时启动
        int result = 0;
        while (objList.size() > 0) {
            // 检测是否出现会相遇的情况
            for (Obj ele : objList) {
                // 检测是否有相遇情况
                meetPro(objList, ele);
            }

            Iterator<Obj> iterator = objList.iterator();
            // 遍历蚂蚁
            while (iterator.hasNext()) {
                Obj obj = iterator.next();

                // 无相遇情况的移动处理
                if (!obj.hasChange) {
                    if (obj.des == 0) { // 向左
                        if (obj.index == 0) {
                            iterator.remove();
                        } else {
                            obj.index = obj.index - 1;
                        }
                    } else { // 向右
                        if (obj.index == n) {
                            iterator.remove();
                        } else {
                            obj.index = obj.index + 1;
                        }
                    }
                }
            }

            if (objList.size() != 0) {
                for (Obj ele : objList) {
                    if (ele.hasChange) {
                        ele.hasChange = false;
                    }
                }
                result = result + 1;
            } else {
                return result;
            }
        }

        return result;
    }

    private void meetPro(ArrayList<Obj> objList, Obj obj) {
        // 已经处理过的不能再次处理
        if (!obj.hasChange) {
            // 遍历查找是否有符合的相遇元素
            for (Obj ele : objList) {
                if (obj.des == 0) { // 向左的找向右元素
                    if (!ele.hasChange && ele.index == obj.index - 1 && ele.des == 1) {
                        ele.des = 0;
                        ele.hasChange = true;
                        obj.des = 1;
                        obj.hasChange = true;
                        break;
                    }
                } else {
                    if (!ele.hasChange && ele.index == obj.index + 1 && ele.des == 0) {
                        ele.des = 1;
                        ele.hasChange = true;
                        obj.des = 0;
                        obj.hasChange = true;
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 4 7 7 5 6 17 16 0 9 29
        System.out.println(
                new GetLastMoment().getLastMoment(4, new int[] {4, 3}, new int[] {0, 1}));
        System.out.println(
                new GetLastMoment()
                        .getLastMoment(7, new int[] {}, new int[] {0, 1, 2, 3, 4, 5, 6, 7}));
        System.out.println(
                new GetLastMoment()
                        .getLastMoment(7, new int[] {0, 1, 2, 3, 4, 5, 6, 7}, new int[] {}));
        System.out.println(new GetLastMoment().getLastMoment(9, new int[] {5}, new int[] {4}));
        System.out.println(new GetLastMoment().getLastMoment(6, new int[] {6}, new int[] {0}));
        System.out.println(new GetLastMoment().getLastMoment(20, new int[] {4}, new int[] {3}));
        System.out.println(new GetLastMoment().getLastMoment(20, new int[] {9, 10}, new int[] {4}));
        System.out.println(new GetLastMoment().getLastMoment(1000, new int[] {0}, new int[] {}));
        System.out.println(
                new GetLastMoment().getLastMoment(10, new int[] {5, 6, 7}, new int[] {1, 2, 4}));
        System.out.println(
                new GetLastMoment()
                        .getLastMoment(
                                30,
                                new int[] {5, 3, 15, 11, 6, 10, 19, 0, 26, 24, 8, 7, 28, 20},
                                new int[] {12, 2, 25, 27, 1, 16, 22, 23, 13, 30, 14, 29, 4, 17}));
        System.out.println(
                new GetLastMoment()
                        .getLastMoment(
                                880,
                                new int[] {
                                    448, 645, 415, 744, 837, 735, 550, 841, 694, 701, 439, 210, 390,
                                    838, 810, 534, 397, 117, 835, 372, 579, 167, 480, 304, 387, 191,
                                    209, 723, 342, 357, 858, 786, 153, 409, 356, 299, 161, 19, 109,
                                    813, 495, 671, 434, 446, 600, 184, 658, 751, 433, 741, 829, 144,
                                    507, 376, 335, 716, 654, 685, 194, 873, 617, 308, 587, 327, 563,
                                    502, 783, 667, 147, 185, 30, 603, 373, 203, 632, 116, 757, 42,
                                    440, 816, 614, 866, 162, 302, 332, 765, 73, 801, 590, 248, 337,
                                    354, 398, 815, 767, 81, 159, 697, 734, 253, 231, 72, 870, 860,
                                    136, 314, 474, 107, 840, 721, 166, 394, 753, 800, 76, 143, 441,
                                    472, 404, 383, 430, 627, 877, 106, 687, 325, 20, 737, 157, 859,
                                    250, 736, 414, 691, 483, 348, 96, 329, 241, 648, 46, 580, 689,
                                    453, 88, 576, 638, 222, 68, 575, 656, 62, 212, 552, 782, 864,
                                    852, 258, 688, 63, 539, 615, 477, 359, 312, 809, 811, 60, 732,
                                    421, 702, 330, 282, 51, 112, 872, 592, 606, 597, 227, 559, 775,
                                    562, 818, 381, 692, 641, 684, 726, 803, 25, 366, 531, 570, 847,
                                    804, 48, 620, 137, 727, 85, 447, 530, 145, 789, 807, 554, 155,
                                    543, 808, 711, 714, 83, 508, 341, 497, 828, 307, 186, 15, 287,
                                    773, 863, 664, 649, 703, 249, 459, 785, 371, 764, 101, 585, 90,
                                    333, 569, 224, 779, 595, 704, 230, 774, 501, 386, 529, 836, 87,
                                    247, 118, 650, 305, 385, 306, 426, 555, 252, 380, 566, 291, 784,
                                    610, 369, 200, 399, 244, 672, 202, 781, 98, 598, 462, 612, 355,
                                    378, 425, 528, 424, 857, 362, 823, 756, 625, 32, 168, 484, 360,
                                    187, 432, 660, 494, 622, 115, 503, 568, 126, 449, 444, 679, 826,
                                    819, 486, 121, 138, 289, 271, 748, 560, 709, 752, 628, 94, 834,
                                    375, 176, 328, 717, 351, 733, 499, 350, 45, 272, 621, 686, 14,
                                    482, 791, 349, 875, 95, 298, 274, 92, 466, 323, 303, 276, 525,
                                    642, 799, 74, 842, 263, 402, 724, 317, 874, 423, 217, 416, 710,
                                    630, 532, 408, 22, 662, 127, 0, 27, 207, 284, 339, 36, 61, 802,
                                    406, 451, 293, 69, 584, 259, 266, 827, 181, 794, 747, 707, 225,
                                    586, 75, 264, 633, 431, 631, 353, 395, 832, 391, 422, 588, 99,
                                    66, 676, 478, 674, 522, 201, 666, 517, 790, 792, 546, 604, 675,
                                    591, 626, 492, 240, 86, 556, 822, 160, 830, 561, 288, 456, 867,
                                    7, 681, 608, 682, 313, 169, 594, 772, 243, 589, 640, 113, 596,
                                    79, 256, 619, 825, 286, 120, 663, 523, 55, 558, 515, 392, 139,
                                    696, 643, 683, 268, 71, 105, 728, 367, 35, 708, 573, 182, 213,
                                    429, 280, 38, 221, 407, 183, 141, 718, 506, 730, 197, 820, 297,
                                    21, 129, 41, 551, 476, 364, 468, 750, 377, 513, 796, 629, 195,
                                    544, 865, 163, 855, 844, 557, 211, 427, 862, 156, 464, 403, 216,
                                    635, 776, 310, 2, 481, 715, 848, 123, 124, 445, 411, 634, 768,
                                    876, 520, 833, 18, 4, 577, 878, 238, 78, 511, 3, 655, 246, 218,
                                    778, 496, 270, 410, 315, 618, 668, 278, 199, 37, 443, 729, 50,
                                    418, 465, 16, 419, 260, 690, 122, 23, 324, 680, 493, 294, 599,
                                    151, 275, 318, 190, 192, 475, 725, 854, 758, 173, 34, 277, 780,
                                    731, 97, 498, 521, 388, 428, 738, 254, 722, 100, 132, 695, 788,
                                    114, 746, 797, 103, 171, 787, 839, 824, 749, 311, 255, 578, 514,
                                    119, 208, 290, 292, 880, 273, 458, 59, 57, 739, 70, 172, 393,
                                    245, 39, 489, 180, 345, 473, 174, 609, 31, 624, 179, 326, 140,
                                    309, 11, 91, 134, 102, 49, 540, 82, 396, 821, 206, 487, 452,
                                    110, 705, 526, 471, 846, 198
                                },
                                new int[] {}));

        System.out.println("时间:" + (System.currentTimeMillis() - start));
    }
}
