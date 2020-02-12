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
     * @ORM\Column(name="ideleve", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $ideleve;

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
    public function getIdeleve()
    {
        return $this->ideleve;
    }

    /**
     * @param int $ideleve
     */
    public function setIdeleve($ideleve)
    {
        $this->ideleve = $ideleve;
    }


    /**
     * Get ideleve
     *
     * @return int
     */
    public function getId()
    {
        return $this->ideleve;
    }


}

