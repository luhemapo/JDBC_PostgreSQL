package edu.unbosque.jdbctutorial.dtos;

public class Vet {

    private String name;
    private String address;
    private String neighborhood;
    private Integer vet_id;

    public Vet(String name, String address, String neighborhood, Integer vet_id) {

        this.name=name;
        this.address=address;
        this.neighborhood=neighborhood;
        this.vet_id=vet_id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getVet_id() {
        return vet_id;
    }

    public void setVet_id(Integer vet_id) {
        this.vet_id = vet_id;
    }
}
