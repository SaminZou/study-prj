package template;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页骨架
 *
 * <p> 页数从 1 开始
 *
 * @author samin
 * @date 2022-06-07
 */
public class  PageProcess {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 977; i++) {
            list.add(String.valueOf(i));
        }

        System.out.println(getPageResp(1, 10, list));
        System.out.println(getPageResp(50, 10, list));
        System.out.println(getPageResp(98, 10, list));
    }

    private static <T> PageResp<T> getPageResp(int page, int size, List<T> list) {
        if (page < 1) {
            page = 1;
        }

        // 结果
        PageResp<T> resp = new PageResp<>();

        resp.setCurrent(page);
        resp.setSize(size);
        resp.setTotal(list.size());

        if (list.size() % size == 0) {
            resp.setPages(list.size() / size);
        } else {
            resp.setPages(list.size() / size + 1);
        }

        List<T> records = new ArrayList<>();
        if (page == resp.getPages()) {
            for (int i = (page - 1) * size; i < (page - 1) * size + list.size() % size; i++) {
                records.add(list.get(i));
            }
        } else {
            for (int i = (page - 1) * size; i < page * size; i++) {
                records.add(list.get(i));
            }
        }
        resp.setRecords(records);

        return resp;
    }

    static class PageResp<T> {

        private Integer current;

        private Integer size;

        private Integer pages;

        private Integer total;

        private List<T> records;

        public Integer getPages() {
            return pages;
        }

        public void setCurrent(Integer current) {
            this.current = current;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public void setPages(Integer pages) {
            this.pages = pages;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public void setRecords(List<T> records) {
            this.records = records;
        }

        @Override
        public String toString() {
            return "PageResp{" + "current=" + current + ", size=" + size + ", pages=" + pages + ", total=" + total
                    + ", records=" + records + '}';
        }
    }
}
