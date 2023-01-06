package com.example.myapplication.Java.Tree;

import java.io.IOException;

public interface ITree {
    void insertElement(Object value, Comparator comparator);
    public Tree Balance();
    boolean deleteElemByInd(int index);
    void printTree();
    public String printTreList();
    boolean deleteElement(Tree.Element search_element);
    public void forEach(Action<Object> a) throws IOException;
    public int getSize();
    public String[] getStringTree();
    public void clear();
}
