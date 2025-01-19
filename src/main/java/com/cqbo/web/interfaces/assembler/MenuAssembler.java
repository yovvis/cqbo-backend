package com.cqbo.web.interfaces.assembler;

import com.cqbo.web.domain.menu.entity.Menu;
import com.cqbo.web.interfaces.dto.menu.MenuAddRequest;
import com.cqbo.web.interfaces.dto.menu.MenuUpdateRequest;
import org.springframework.beans.BeanUtils;

/**
 * 用户对象转换
 */
public class MenuAssembler {

    public static Menu toMenuEntity(MenuAddRequest request) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(request, menu);
        return menu;
    }

    public static Menu toMenuEntity(MenuUpdateRequest request) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(request, menu);
        return menu;
    }
}