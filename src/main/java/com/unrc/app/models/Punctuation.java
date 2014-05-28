package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Punctuation extends Model {
  static {

      validatePresenceOf("point_u");
    

      
  }
  //retorna el modelo Punctuation 
	public static Punctuation findByPunctuation(int ident){
		return (findFirst("id = ?", ident));
	}


  
    //a partir de los puntos a otorgar el usuario que otorga y y el usuario que recibe 
    //crea una puntuacion nueva siempre y cuando ésta no exista en la bd
	public static Punctuation createPunctuation(int point, Post publication, User giver){
    	Punctuation puntuacion=create("point_u", point,"id_user", giver.getInteger("id"), "id_user_receiver", publication.getInteger("id_users"));
        puntuacion.saveIt();
        return findByPunctuation(puntuacion.getInteger("id"));
    }

    public static boolean existPunctuation(Post publication, User giver){
        Boolean status=false;
         if( Punctuation.first("id_user = ? and id_user_receiver = ?",giver.getInteger("id"), publication.getInteger("id_users"))==null){
            return true;
         }
        return status;
    }



              
}