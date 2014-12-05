//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.5-2 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.16 à 02:38:27 PM CET 
//


package org.transgalactica.flux.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour EmployeCommand complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EmployeCommand">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateEmbauche" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="employeType" type="{}employeType" minOccurs="0"/>
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeCommand", propOrder = {
    "dateEmbauche",
    "employeType",
    "nom"
})
@XmlSeeAlso({
    PiloteCommand.class
})
public class EmployeCommand {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateEmbauche;
    protected EmployeType employeType;
    protected String nom;

    /**
     * Obtient la valeur de la propriété dateEmbauche.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateEmbauche() {
        return dateEmbauche;
    }

    /**
     * Définit la valeur de la propriété dateEmbauche.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateEmbauche(XMLGregorianCalendar value) {
        this.dateEmbauche = value;
    }

    /**
     * Obtient la valeur de la propriété employeType.
     * 
     * @return
     *     possible object is
     *     {@link EmployeType }
     *     
     */
    public EmployeType getEmployeType() {
        return employeType;
    }

    /**
     * Définit la valeur de la propriété employeType.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeType }
     *     
     */
    public void setEmployeType(EmployeType value) {
        this.employeType = value;
    }

    /**
     * Obtient la valeur de la propriété nom.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de la propriété nom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNom(String value) {
        this.nom = value;
    }

}
