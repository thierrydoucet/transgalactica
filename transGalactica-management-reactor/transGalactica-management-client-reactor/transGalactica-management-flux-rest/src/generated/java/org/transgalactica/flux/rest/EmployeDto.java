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
 * <p>Classe Java pour EmployeDto complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EmployeDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateEmbauche" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="matricule" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeEmploye" type="{}employeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeDto", propOrder = {
    "dateEmbauche",
    "matricule",
    "nom",
    "typeEmploye"
})
@XmlSeeAlso({
    MecanicienDetailDto.class,
    PiloteDetailDto.class
})
public class EmployeDto {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateEmbauche;
    protected Long matricule;
    protected String nom;
    protected EmployeType typeEmploye;

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
     * Obtient la valeur de la propriété matricule.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMatricule() {
        return matricule;
    }

    /**
     * Définit la valeur de la propriété matricule.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMatricule(Long value) {
        this.matricule = value;
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

    /**
     * Obtient la valeur de la propriété typeEmploye.
     * 
     * @return
     *     possible object is
     *     {@link EmployeType }
     *     
     */
    public EmployeType getTypeEmploye() {
        return typeEmploye;
    }

    /**
     * Définit la valeur de la propriété typeEmploye.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeType }
     *     
     */
    public void setTypeEmploye(EmployeType value) {
        this.typeEmploye = value;
    }

}
