//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.12.22 à 09:37:21 PM CET 
//


package org.transgalactica.management.flux.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour HangarVaisseau complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HangarVaisseau"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="immatriculation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="modele" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nombreDePassagers" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *         &lt;element name="capaciteDeFret" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="vitesse" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="autonomie" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HangarVaisseau", namespace = "http://management.transgalactica.org/logistics", propOrder = {
    "immatriculation",
    "modele",
    "nombreDePassagers",
    "capaciteDeFret",
    "vitesse",
    "autonomie"
})
public class HangarVaisseau {

    @XmlElement(required = true)
    protected String immatriculation;
    @XmlElement(required = true)
    protected String modele;
    protected short nombreDePassagers;
    protected long capaciteDeFret;
    protected int vitesse;
    protected int autonomie;

    /**
     * Obtient la valeur de la propriété immatriculation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Définit la valeur de la propriété immatriculation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImmatriculation(String value) {
        this.immatriculation = value;
    }

    /**
     * Obtient la valeur de la propriété modele.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModele() {
        return modele;
    }

    /**
     * Définit la valeur de la propriété modele.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModele(String value) {
        this.modele = value;
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

}
