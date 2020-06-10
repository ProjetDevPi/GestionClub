<?php

namespace ClubBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Equipement
 *
 * @ORM\Table(name="equipement")
 * @ORM\Entity(repositoryClass="ClubBundle\Repository\EquipementRepository")
 */
class Equipement
{
    /**
     * @var int
     *
     * @ORM\Column(name="ideq", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $ideq;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255)
     */
    private $type;

    /**
     * @ORM\ManyToOne(targetEntity="Club")
     * @ORM\JoinColumn(name="idclub", referencedColumnName="idclub")
     */
    private $idclub;

    /**
     * @return mixed
     */
    public function getIdclub()
    {
        return $this->idclub;
    }

    /**
     * @param mixed $idclub
     */
    public function setIdclub($idclub)
    {
        $this->idclub = $idclub;
    }

    /**
     * @return int
     */
    public function getIdeq()
    {
        return $this->ideq;
    }

    /**
     * @param int $ideq
     */
    public function setIdeq($ideq)
    {
        $this->ideq = $ideq;
    }


    /**
     * Get ideq
     *
     * @return int
     */
    public function getId()
    {
        return $this->ideq;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Equipement
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set type
     *
     * @param string $type
     *
     * @return Equipement
     */
    public function setType($type)
    {
        $this->type = $type;

        return $this;
    }

    /**
     * Get type
     *
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }


}

