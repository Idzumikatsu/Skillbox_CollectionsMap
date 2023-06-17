package practice;

import java.util.*;

public class PhoneBook {
    private TreeMap<String, String> map = new TreeMap<>();

    public void addContact(String phone, String name) {
        boolean isContactValid = isPhoneNumber(phone) && isName(name);

        if (map.containsValue(name)) {
            map.remove(phone);
            map.put(getPhoneByName(name), name);
        }
        if (isContactValid && map.containsKey(phone)) {
            map.replace(phone, name);
        }
        if (isContactValid && !map.containsKey(phone)) {
            map.put(phone, name);
        }
    }

    public String getContactByPhone(String phone) {
        if (map.containsKey(phone)) {
            return map.get(phone).concat(" - ").concat(phone);
        }
        return "";
    }

    public Set<String> getContactByName(String name) {
        var treeSet = new HashSet<String>();
        for (var entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (name.equals(value)) {
                treeSet.add(value.concat(" - ").concat(key));
            }
            return treeSet;
        }
        return Collections.emptySet();
    }

    public Set<String> getAllContacts() {
        if (map.isEmpty()) {
            return Collections.emptySet();
        }

        TreeSet<String> contactsSet = new TreeSet<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            contactsSet.add(value.concat(" - ").concat(getPhoneByName(value)));
        }
        return contactsSet;
    }

    public boolean isPhoneNumber(String phone) {
        String phoneRegExp = "7[0-9]{10}";
        return phone.matches(phoneRegExp);
    }

    public boolean isName(String name) {
        String nameRexExp = "[А-Яа-я]+";
        return name.toLowerCase().matches(nameRexExp);
    }

    private String getPhoneByName(String name) {
        String phone = "";
        for (Map.Entry<String,String> entry : map.entrySet()) {
            if (map.get(entry.getKey()).equals(name)){
                phone = phone.concat(entry.getKey()).concat(", ");
            }
        }
        if (phone.endsWith(", ")) {
            phone = phone.substring(0,phone.lastIndexOf(", "));
        }
        return phone;
    }

    public Collection<String> getNames () {
        return map.values();
    }

    public Collection<String> getPhones () {
        return map.keySet();
    }
}