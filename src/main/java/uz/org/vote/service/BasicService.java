package uz.org.vote.service;

import uz.org.vote.message.ResponseMessage;

public interface BasicService<T> {
    ResponseMessage getAll();
    ResponseMessage save(T t);
    ResponseMessage delete(int id);
    ResponseMessage update(T t);
}
