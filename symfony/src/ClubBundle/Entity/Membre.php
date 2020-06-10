<?php

namespace ClubBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
/**
 * Membre
 *
 * @ORM\Table(name="membre")
 * @ORM\Entity(repositoryClass="ClubBundle\Repository\MembreRepository")
 */
class Membre
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }


    /**
     * @ORM\ManyToOne(targetEntity="club")
     *
     * @ORM\JoinColumn(name="idclub",referencedColumnName="idclub")
     */
    private $idclub;

    /**
     * @return mixed
     */
    public function getEleve()
    {
        return $this->eleve;
    }

    /**
     * @param mixed $eleve
     */
    public function setEleve($eleve)
    {
        $this->eleve = $eleve;
    }
    /**
     * @ORM\ManyToOne(targetEntity="eleve")
     *
     * @ORM\JoinColumn(name="ideleve",referencedColumnName="id")
     */
    private $eleve;

    /**
     * @ORM\ManyToOne(targetEntity="ClubBundle\Entity\User")
     *
     * @ORM\JoinColumn(name="id_user",referencedColumnName="id")
     */
    private $idUser;


    /**
     * Set idclub
     *
     * @param \ClubBundle\Entity\Club $idclub
     *
     * @return Membre
     */
    public function setIdclub(\ClubBundle\Entity\Club $idclub = null)
    {
        $this->idclub = $idclub;

        return $this;
    }

    /**
     * Get idclub
     *
     * @return \ClubBundle\Entity\Club
     */
    public function getIdclub()
    {
        return $this->idclub;
    }

    /**
     * Set idUser
     *
     * @param \ClubBundle\Entity\User $idUser
     *
     * @return Membre
     */
    public function setIdUser(\ClubBundle\Entity\User $idUser = null)
    {
        $this->idUser = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return \ClubBundle\Entity\User
     */
    public function getIdUser()
    {
        return $this->idUser;
    }



}
