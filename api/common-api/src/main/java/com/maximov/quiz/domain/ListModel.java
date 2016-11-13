package com.maximov.quiz.domain;

/**
 * Модель списка с пагинацией
 */
public class ListModel implements BaseModel {

    /**
     * Номер страницы
     */
    private Integer pageNumber;

    /**
     * Количество элементов на странице
     */
    private Integer itemsByPage;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getItemsByPage() {
        return itemsByPage;
    }

    public void setItemsByPage(Integer itemsByPage) {
        this.itemsByPage = itemsByPage;
    }
}
