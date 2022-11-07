package com.bounteous.model;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.List;
import java.util.stream.Collectors;

public class AEMPageModel extends AEMContainerComponent {
    private String language;
    private Long lastModifiedDate;
    private String title;
    private String designPath;
    private String cssClassNames;
    private String templateName;

    @SerializedName(":hierarchyType")
    private String hierarchyType;

    @SerializedName(":path")
    private String path;

    @GraphQLQuery
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @GraphQLQuery
    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @GraphQLQuery
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @GraphQLQuery
    public String getDesignPath() {
        return designPath;
    }

    public void setDesignPath(String designPath) {
        this.designPath = designPath;
    }

    @GraphQLQuery
    public String getCssClassNames() {
        return cssClassNames;
    }

    public void setCssClassNames(String cssClassNames) {
        this.cssClassNames = cssClassNames;
    }

    @GraphQLQuery
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @GraphQLQuery(name="hierarchyType")
    public String getHierarchyType() {
        return hierarchyType;
    }

    public void setHierarchyType(String hierarchyType) {
        this.hierarchyType = hierarchyType;
    }

    @GraphQLQuery(name="path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public List<String> getChildrenPaths() {
        return getItems().stream().map(item -> "/jcr:content/"+item.getKey()).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AEMPageModel pageModel = (AEMPageModel) o;
        return Objects.equal(path, pageModel.path);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(path);
    }

}
