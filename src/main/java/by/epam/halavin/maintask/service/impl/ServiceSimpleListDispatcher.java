package by.epam.halavin.maintask.service.impl;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.repository.SimpleRepository;
import by.epam.halavin.maintask.service.ServiceListDispatcher;
import by.epam.halavin.maintask.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ServiceSimpleListDispatcher implements ServiceListDispatcher {
    private int currentPosition = 0;
    private SimpleRepository repository;
    private String source = "";

    public ServiceSimpleListDispatcher() {
        super();
    }

    public ServiceSimpleListDispatcher(SimpleRepository repository) {
        this.repository = repository;
    }

    public void setRepository(SimpleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setSource(String str) {
        source = str;
    }

    @Override
    public List<User> getNextUserListItems() throws ServiceException {
        List<User> list;

        try {
            list = repository.getList();

            if (currentPosition >= list.size()) {
                currentPosition = 0;
            }
            if (currentPosition + ServiceSimpleListDispatcher.NUMBER_PLUS > list.size()) {
                int num = currentPosition;
                currentPosition += ServiceSimpleListDispatcher.NUMBER_PLUS;

                return list.subList(num, list.size());
            }

            list = repository.getList().subList(currentPosition,
                    currentPosition += ServiceSimpleListDispatcher.NUMBER_PLUS);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;
    }

    @Override
    public List<User> getCurrentUserListItems() throws ServiceException {
        int firstIndex = currentPosition - ServiceSimpleListDispatcher.NUMBER_PLUS;
        int secondIndex = currentPosition;
        List<User> list;

        try {
            list = repository.getList();

            if (currentPosition >= list.size()) {
                firstIndex = currentPosition - ServiceSimpleListDispatcher.NUMBER_PLUS;
                secondIndex = list.size();
            } else if (currentPosition - ServiceSimpleListDispatcher.NUMBER_PLUS < 0) {
                firstIndex = currentPosition;
                secondIndex = currentPosition + ServiceSimpleListDispatcher.NUMBER_PLUS;
            }

            list = repository.getList().subList(firstIndex, secondIndex);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public List<User> getNumberOfUsers(int count) throws ServiceException {
        currentPosition = (count) * ServiceListDispatcher.NUMBER_PLUS;
        return getCurrentUserListItems();
    }

    @Override
    public List<Integer> getUserCount() throws ServiceException {
        List<Integer> blocks = new ArrayList<>();
        int number = ServiceListDispatcher.NUMBER_PLUS;

        try {
            int numberOfBlocks = (int) Math.ceil(repository.getList().size() * 1. / ServiceListDispatcher.NUMBER_PLUS);

            for (int i = 0; i < numberOfBlocks; i++) {
                blocks.add(i + 1);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return blocks;
    }
}
