package org.example;

public record Client(String name, String family, int idNumer) {
public Client{
    if (name==null){
        throw new IllegalArgumentException("name is null");
    }
    if (family==null){
        throw new IllegalArgumentException("family is null");
    }
    if (idNumer<0){
        throw new IllegalArgumentException("idNumer is negative");
    }
}
    public Client withNewFamily(String newFamily){
        if (newFamily  == null){ throw new IllegalArgumentException("newFamily is null"); }
return  new Client(newFamily,this.family,this.idNumer);
}

}
