//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.12.22 à 09:37:21 PM CET 
//


package org.transgalactica.flux.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour VaisseauDetailDto complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VaisseauDetailDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://management.transgalactica.org/logistics}VaisseauDto"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numeroHangar" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="nombreDePassagers" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *         &lt;element name="capaciteDeFret" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="vitesse" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="autonomie" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="multiplicateurHyperdrive" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VaisseauDetailDto", namespace = "http://management.transgalactica.org/logistics", propOrder = {
    "numeroHangar",
    "nombreDePassagers",
    "capaciteDeFret",
    "vitesse",
    "autonomie",
    "multiplicateurHyperdrive"
})
public class VaisseauDetailDto
    extends VaisseauDto
{

    protected long numeroHangar;
    protected short nombreDePassagers;
    protected long capaciteDeFret;
    protected int vitesse;
    protected int autonomie;
    protected short multiplicateurHyperdrive;

    /**
     * Obtient la valeur de la propriété numeroHangar.
     * 
     */
    public long getNumeroHangar() {
        return numeroHangar;
    }

    /**
     * Définit la valeur de la propriété numeroHangar.
     * 
     */
    public void setNumeroHangar(long value) {
        this.numeroHangar = value;
    }

    /**
     * Obtient la valeur de la propriété nombreDePassagers.
     * 
     */
    public short getNombreDePassagers() {
        return nombreDePassagers;
    }

    /**
     * Définit la valeur de la propriété nombreDePassagers.
     * 
     */
    public void setNombreDePassagers(short value) {
        this.nombreDePassagers = value;
    }

    /**
     * Obtient la valeur de la propriété capaciteDeFret.
     * 
     */
    public long getCapaciteDeFret() {
        return capaciteDeFret;
    }

    /**
     * Définit la valeur de la propriété capaciteDeFret.
     * 
     */
    public void setCapaciteDeFret(long value) {
        this.capaciteDeFret = value;
    }

    /**
     * Obtient la valeur de la propriété vitesse.
     * 
     */
    public int getVitesse() {
        return vitesse;
    }

    /**
     * Définit la valeur de la propriété vitesse.
     * 
     */
    public void setVitesse(int value) {
        this.vitesse = value;
    }

    /**
     * Obtient la valeur de la propriété autonomie.
     * 
     */
    public int getAutonomie() {
        return autonomie;
    }

    /**
     * Définit la valeur de la propriété autonomie.
     * 
     */
    public void setAutonomie(int value) {
        this.autonomie = value;
    }

    /**
     * Obtient la valeur de la propriété multiplicateurHyperdrive.
     * 
     */
    public short getMultiplicateurHyperdrive() {
        return multiplicateurHyperdrive;
    }

    /**
     * Définit la valeur de la propriété multiplicateurHyperdrive.
     * 
     */
    public void setMultiplicateurHyperdrive(short value) {
        this.multiplicateurHyperdrive = value;
    }

}
