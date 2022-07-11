package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialitiesService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final SpecialitiesService specialitiesService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, SpecialitiesService specialitiesService, PetTypeService petTypeService) {
//        ownerService = new OwnerServiceMap();
//        vetService = new VetServiceMap();
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.specialitiesService = specialitiesService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialitiesService.save(radiology);
        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialitiesService.save(surgery);
        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialitiesService.save(dentistry);

        Owner owner1 = new Owner();
//        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brick");
        owner1.setCity("1city");
        owner1.setTelephone("1231232123");
        System.out.println(owner1);

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
//        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("222 Brick");
        owner2.setCity("2city");
        owner2.setTelephone("1231232123");

        Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setPetType(savedCatPetType);
        owner2.getPets().add((fionasCat));

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
//        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        System.out.println(vet1);

        vetService.save(vet1);

        Vet vet2 = new Vet();
//        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);

        System.out.println(vet2);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
