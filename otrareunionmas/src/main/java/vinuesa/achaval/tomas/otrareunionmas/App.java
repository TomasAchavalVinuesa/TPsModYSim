package vinuesa.achaval.tomas.otrareunionmas;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import vinuesa.achaval.tomas.otrareunionmas.dao.ActaDao;
import vinuesa.achaval.tomas.otrareunionmas.dao.ReunionDao;
import vinuesa.achaval.tomas.otrareunionmas.dao.SalaDao;
import vinuesa.achaval.tomas.otrareunionmas.dominio.Acta;
import vinuesa.achaval.tomas.otrareunionmas.dominio.Persona;
import vinuesa.achaval.tomas.otrareunionmas.dominio.Reunion;
import vinuesa.achaval.tomas.otrareunionmas.dominio.Sala;

public class App {
	
    public static void main( String[] args ){
    	ReunionDao reunionDao = new ReunionDao();
    	ActaDao actaDao =new ActaDao();
    	SalaDao salaDao = new SalaDao();
    	
    	Sala s099 = new Sala("S099", "Trastero", 1);
    	Sala s101 = new Sala("S101", "Reunion primera planta", 10);
    	Sala s109 = new Sala("S109", "Entrevistas primera planta", 3);
    	Sala s203 = new Sala("S203", "Sala grande", 25);
    	
    	salaDao.save(s099);
    	salaDao.save(s101);
    	salaDao.save(s109);
    	salaDao.save(s203);
    	
    	Persona marta = new Persona ("E001", "Marta", "García López");
    	Persona pedro = new Persona ("E002", "Pedro", "Gómez Fernández");
    	Persona santi = new Persona ("E003", "Santi", "Achával Gutiérez");
    	Persona luisa = new Persona ("E004", "Luisa", "González Vinuesa");
    	
    	Reunion r0 = new Reunion(LocalDateTime.now(), "Reunión de Test");
    	Reunion r1 = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.HOURS), "Otra reunión de Test");
    	Reunion r2 = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.DAYS), "Reunión de pasado mañana");
    	Reunion r3 = new Reunion(LocalDateTime.now().plus(1, ChronoUnit.DAYS), "Reunión de mañana");
    	Reunion r4 = new Reunion(LocalDateTime.now().minus(1, ChronoUnit.DAYS), "Reunión de ayer");
    	
    	r0.addParticipante(marta);
    	r0.setSala(s099);
    	reunionDao.save(r0);
    	Acta a0 = new Acta("Marta se reune sola, solo para descansar un rato", r0);
    	actaDao.save(a0);
    	reunionDao.update(r0);
    	
    	r1.addParticipante(marta);
    	r1.addParticipante(pedro);
    	r1.addParticipante(santi);
    	r1.addParticipante(luisa);
    	r1.setSala(s101);
    	reunionDao.save(r1);
    	
    	r2.addParticipante(santi);
    	r2.addParticipante(pedro);
    	r2.setSala(s109);
    	reunionDao.save(r2);
    	
    	r3.addParticipante(marta);
    	r3.addParticipante(luisa);
    	r3.setSala(s109);
    	reunionDao.save(r3);
    	
    	r4.addParticipante(marta);
    	r4.addParticipante(pedro);
    	r4.addParticipante(santi);
    	r4.addParticipante(luisa);
    	r4.setSala(s203);
    	reunionDao.save(r4);
    	
    	Acta a4 = new Acta("Participantes: x duración: Y, etc ", r4);
    	actaDao.save(a4);
    	reunionDao.update(r4);
    	
    	List<Reunion> reuniones = reunionDao.getAll();
    	System.out.println("Todas las reuniones: " + reuniones);
    	
    	try {
    		System.out.println("Tu próxima Reunión es: " + reunionDao.proximaReunion());
    	} catch(NoResultException nre) {
    		List<Reunion> reunionesManyana = reunionDao.reunionesManyana();
    		System.out.println("Para mañana: " + reunionesManyana);
    	}
    }
}



/*
 *  ReunionDao dao = new ReunionDao();
        List<Reunion> reuniones2 = dao.getAll();
        System.out.println("*** "+ reuniones2);
        
        Persona marta = new Persona("E0011", "Marta", "Garcia Lopez");
        Persona pedro = new Persona("E0022", "Pedro", "Gomez Fernandez");
        
        Reunion r = new Reunion(LocalDateTime.now(), "Reunion de test");
        System.out.println(r);
        r.addParticipante(marta);
        r.addParticipante(pedro);
        dao.save(r); 
        System.out.println(r);
        
        ActaDao actaDao = new ActaDao();
        Acta a = new Acta("Reunión anulada", r);
        actaDao.save(a);
       
        Reunion r1 = new Reunion(LocalDateTime.now(), "Otra reunipon de Test");
        pedro.addReunion(r1);
        dao.save(r1);
        
        
        Reunion r2 = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.DAYS), "reunion para pasado mañana");
        System.out.println(r2);
        dao.save(r2);
        try {
        System.out.println("Tu proxima reunión es: " + dao.proximaReunion());
        } catch (NoResultException nre) {
        	System.out.println("No tienes ninguna reunión pronto");
        }
        
        Reunion r3 = new Reunion(LocalDateTime.now().plus(1, ChronoUnit.DAYS), "Reunión de mañana");
        dao.save(r3);
        
        List<Reunion> reunionManyana = dao.reunionesManyana();
        System.out.println("Para mañana: " + reunionManyana);
        
//        SalaDao salaDao = new SalaDao();
//        Sala s = new Sala("S201", "Sala grande", 25);
//        salaDao.save(s);
//        System.out.println("paso 1 " + salaDao.getAll());
//        
//        s.setDescripcion("Sala grande reformada");
//        salaDao.update(s);
//        System.out.println("Paso 2 " + salaDao.getAll());
//        
//        Sala s2 = new Sala("99", "Trastero", 1);
//        salaDao.save(s2);
//        System.out.println("Paso 3 " + salaDao.getAll());
//        
//        salaDao.delete(s2);
//        
//        System.out.println("Paso 4 " + salaDao.getAll());
        */