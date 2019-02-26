/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author NacimGastli
 */
public interface CRUD <T>{   
    int insert(T t);
    void Delete(T t, int id);
    int update(T t);
    int SingIn(T t);
    int VerifierCompte(T t,String code);
    T chercher(int id);
   
    List<T> getshow();
    
}