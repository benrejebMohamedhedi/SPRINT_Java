/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Client;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author NacimGastli
 */
public interface CRUD <T>{   
    int insert(T t);
    void Delete(int id);
    int update(T t);
    int SingIn(T t);
    int VerifierCompte(T t,String code);
    T chercher(int id);
    String getMd5(String mdp);
    List<T> getshow();
    
}