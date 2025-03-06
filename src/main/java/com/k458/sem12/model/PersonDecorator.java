package com.k458.sem12.model;


public class PersonDecorator extends Person{

    private Person superPerson;

    public PersonDecorator(Person p){
        this.superPerson = p;
    }

    @Override
    public String toString() {
        return "PREFIX_"+superPerson.toString()+"_POSTFIX";
    }

    @Override
    public void setId(Long id) {
        superPerson.setId(id);
    }

    @Override
    public Long getId() {
        return superPerson.getId();
    }

    @Override
    public void setName(String name) {
        superPerson.setName(name);
    }

    @Override
    public void setCreatedTime(String createdTime) {
        superPerson.setCreatedTime(createdTime);
    }

    @Override
    public String getCreatedTime() {
        return superPerson.getCreatedTime();
    }

    @Override
    public String getName() {
        return superPerson.getName();
    }
}
