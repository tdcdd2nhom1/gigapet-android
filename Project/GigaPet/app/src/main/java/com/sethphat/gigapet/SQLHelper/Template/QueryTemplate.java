package com.sethphat.gigapet.SQLHelper.Template;

import java.util.ArrayList;

public interface QueryTemplate<T> {

    void Insert(T info);
    void Update(T info);
    void Delete(int id);
    ArrayList<T> GetAll();
    T GetByID(int id);
}
