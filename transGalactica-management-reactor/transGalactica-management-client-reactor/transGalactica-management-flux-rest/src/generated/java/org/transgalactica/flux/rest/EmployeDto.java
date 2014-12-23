//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.12.22 à 09:37:21 PM CET 
//


package org.transgalactica.flux.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * &lt;complexType name="EmployeDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="matricule" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dateEmbauche" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="typeEmploye" type="{}employeType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeDto", propOrder = {
    "matricule",
    "nom",
    "dateEmbauche",
    "typeEmploye"
})
@XmlSeeAlso({
    PiloteDetailDto.class,
    MecanicienDetailDto.class
})
public class EmployeDto {

    protected long matricule;
    @XmlElement(required = true)
    protected String nom;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateEmbauche;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EmployeType typeEmploye;

    /**
     * Obtient la valeur de la propriété matricule.
     * 
     */
    public long getMatricule() {
        return matricule;
    }

    /**
     * Définit la valeur de la propriété matricule.
     * 
     */
    public void setMatricule(long value) {
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
