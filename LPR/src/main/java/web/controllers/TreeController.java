package web.controllers;

import web.service.TreeService;

public class TreeController {
    private final TreeService treeService;

    public TreeController(final TreeService treeService) {
        this.treeService = treeService;
    }
}
