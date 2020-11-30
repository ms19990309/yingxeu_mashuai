package com.baizhi.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPO {

    private String id;
    private String cateName;
    private String levels;
    private String parentId;
    private List<CategoryPO> categoryList;

}
