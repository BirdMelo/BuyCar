package br.com.alura.buyCar.service;

public class BooleanStrToInt {
    public boolean isInteger(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
