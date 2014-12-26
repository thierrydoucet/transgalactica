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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour HangarDto complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HangarDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="localisation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nombreEmplacements" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HangarDto", namespace = "http://management.transgalactica.org/logistics", propOrder = {
    "numero",
    "localisation",
    "nombreEmplacements"
})
@XmlSeeAlso({
    HangarDetailDto.class
})
public class HangarDto {

    protected long numero;
    @XmlElement(required = true)
    protected String localisation;
    protected int nombreEmplacements;

    /**
     * Obtient la valeur de la propriété numero.
     * 
     */
    public long getNumero() {
        return numero;
    }

    /**
     * Définit la valeur de la propriété numero.
     * 
     */
    public void setNumero(long value) {
        this.numero = value;
    }

    /**
     * Obtient la valeur de la propriété localisation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalisation() {
        return localisation;
    }

    /**
     * Définit la valeur de la propriété localisation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalisation(String value) {
        this.localisation = value;
    }

    /**
     * Obtient la valeur de la propriété nombreEmplacements.
     * 
     */
    public int getNombreEmplacements() {
        return nombreEmplacements;
    }

    /**
     * Définit la valeur de la propriété nombreEmplacements.
     * 
     */
    public void setNombreEmplacements(int value) {
        this.nombreEmplacements = value;
    }

}
