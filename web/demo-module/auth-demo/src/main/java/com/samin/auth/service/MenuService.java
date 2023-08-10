package com.samin.auth.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.samin.auth.entity.Menu;
import com.samin.auth.repo.MenuRepository;
import com.samin.auth.vo.req.MenuSaveReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 菜单服务类
 *
 * @author samin
 * @date 2023-07-23
 */
@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    /**
     * 保存菜单
     *
     * @param menuVos 菜单信息
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void saveMenu(List<MenuSaveReq> menuVos) {
        if (CollectionUtils.isEmpty(menuVos)) {
            return;
        }

        List<Menu> menus = menuVos.stream()
                .map(e -> {
                    Menu menu = null;
                    if (Objects.nonNull(e.getId()) && e.getId() != 0) {
                        Optional<Menu> menuOptional = menuRepository.findById(e.getId());

                        if (menuOptional.isPresent()) {
                            menu = menuOptional.get();
                            CopyOptions options = CopyOptions.create()
                                    .ignoreNullValue()
                                    .setIgnoreProperties("code");
                            BeanUtil.copyProperties(e, menu, options);
                        }
                    } else {
                        menu = new Menu();
                        CopyOptions options = CopyOptions.create()
                                .ignoreNullValue();
                        BeanUtil.copyProperties(e, menu, options);
                    }

                    return menu;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        menuRepository.saveAll(menus);
    }
}