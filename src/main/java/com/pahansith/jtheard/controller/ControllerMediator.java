package com.pahansith.jtheard.controller;

import java.util.ArrayList;
import java.util.List;

public class ControllerMediator {

    private static ControllerMediator controllerMediator;
    private List<AbstractController> controllerList = new ArrayList<>();

    private ControllerMediator() {
    }

    public void registerController(AbstractController controller) {
        int index = controllerList.indexOf(controller);
        if (index == -1) {
            controllerList.add(controller);
        } else {
            controllerList.remove(index);
            controllerList.add(controller);
        }

    }

    public Object getController(Class<?> controllerClass) throws IllegalAccessException, InstantiationException {
        Object instance = controllerClass.newInstance();
        for (AbstractController obj : controllerList) {
            if (obj.equals(instance)) {
                return obj;
            }
        }
        return null;
    }


    public static ControllerMediator getInstance() {
        if (controllerMediator == null) {
            controllerMediator = new ControllerMediator();
        }
        return controllerMediator;
    }
}
