package com.bounteous.model;

import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.LinkedList;



public class AEMHeader extends AEMComponent {

    private LinkedList<AEMNavItem> items;

    @GraphQLQuery
    public LinkedList<AEMNavItem> getItems() {
        return items;
    }

    public void setItems(LinkedList<AEMNavItem> items) {
        this.items = items;
    }

    public class AEMNavItem {
        private LinkedList<AEMNavItem> children;
        private int level;
        private boolean active;
        private String path;
        private Long lastModified;
        private String url;
        private String title;

        @GraphQLQuery
        public LinkedList<AEMNavItem> getChildren() {
            return children;
        }

        public void setChildren(LinkedList<AEMNavItem> children) {
            this.children = children;
        }

        @GraphQLQuery
        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        @GraphQLQuery
        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        @GraphQLQuery
        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        @GraphQLQuery
        public Long getLastModified() {
            return lastModified;
        }

        public void setLastModified(Long lastModified) {
            this.lastModified = lastModified;
        }

        @GraphQLQuery
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @GraphQLQuery
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
