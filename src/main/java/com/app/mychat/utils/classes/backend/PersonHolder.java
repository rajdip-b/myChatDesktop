package com.app.mychat.utils.classes.backend;

import com.app.mychat.utils.classes.ui.Person;

import java.util.ArrayList;

public class PersonHolder {

    private static ArrayList<Person> personArrayList;

    public static void addPerson(Person person){
        if (personArrayList == null)
            personArrayList = new ArrayList<>();
        personArrayList.add(person);
    }

    public static Person getPersonByUsername(String username){
        for (Person person: personArrayList)
            if (person.getUsername().equals(username))
                return person;
        return null;
    }

}
