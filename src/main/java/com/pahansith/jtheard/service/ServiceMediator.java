package com.pahansith.jtheard.service;

import java.util.ArrayList;
import java.util.List;

public class ServiceMediator {

    private static ServiceMediator serviceMediator;
    private List<AbstractService> serviceList = new ArrayList<>();

    private ServiceMediator() {
    }

    public void registerService(AbstractService service) {
        int index = serviceList.indexOf(service);
        if (index == -1) {
            serviceList.add(service);
        } else {
            serviceList.remove(index);
            serviceList.add(service);
        }

    }

    public Object getService(Class<?> serviceClass) throws IllegalAccessException, InstantiationException {
        Object instance = serviceClass.newInstance();
        for (AbstractService obj : serviceList) {
            if (obj.equals(instance)) {
                return obj;
            }
        }
        return null;
    }


    public static ServiceMediator getInstance() {
        if (serviceMediator == null) {
            serviceMediator = new ServiceMediator();
        }
        return serviceMediator;
    }
}
