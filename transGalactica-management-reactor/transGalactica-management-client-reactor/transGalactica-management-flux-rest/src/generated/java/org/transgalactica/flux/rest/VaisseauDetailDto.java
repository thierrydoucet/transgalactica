//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.5-2 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.16 à 02:38:27 PM CET 
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
 * &lt;complexType name="VaisseauDetailDto">
 *   &lt;complexContent>
 *     &lt;extension base="{}VaisseauDto">
 *       &lt;sequence>
 *         &lt;element name="capaciteDeFret" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="multiplicateurHyperdrive" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="nombreDePassagers" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="numeroHangar" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="autonomie" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vitesse" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VaisseauDetailDto", propOrder = {
    "capaciteDeFret",
    "multiplicateurHyperdrive",
    "nombreDePassagers",
    "numeroHangar",
    "autonomie",
    "vitesse"
})
public class VaisseauDetailDto
    extends VaisseauDto
{

    protected long capaciteDeFret;
    protected Short multiplicateurHyperdrive;
    protected short nombreDePassagers;
    protected Long numeroHangar;
    protected int autonomie;
    protected int vitesse;

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
     * Obtient la valeur de la propriété multiplicateurHyperdrive.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMultiplicateurHyperdrive() {
        return multiplicateurHyperdrive;
    }

    /**
     * Définit la valeur de la propriété multiplicateurHyperdrive.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMultiplicateurHyperdrive(Short value) {
        this.multiplicateurHyperdrive = value;
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
     * Obtient la valeur de la propriété numeroHangar.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumeroHangar() {
        return numeroHangar;
    }

    /**
     * Définit la valeur de la propriété numeroHangar.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumeroHangar(Long value) {
        this.numeroHangar = value;
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

}
