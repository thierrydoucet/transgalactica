//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.5-2 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.16 à 02:38:27 PM CET 
//


package org.transgalactica.flux.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour VaisseauDto complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VaisseauDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="immatriculation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localisationHangar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modele" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VaisseauDto", propOrder = {
    "immatriculation",
    "localisationHangar",
    "modele"
})
@XmlSeeAlso({
    VaisseauDetailDto.class
})
public class VaisseauDto {

    protected String immatriculation;
    protected String localisationHangar;
    protected String modele;

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
     * Obtient la valeur de la propriété localisationHangar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalisationHangar() {
        return localisationHangar;
    }

    /**
     * Définit la valeur de la propriété localisationHangar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalisationHangar(String value) {
        this.localisationHangar = value;
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

}
