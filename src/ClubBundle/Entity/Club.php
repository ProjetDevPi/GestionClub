<?php

namespace ClubBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Club
 *
 * @ORM\Table(name="club")
 * @ORM\Entity(repositoryClass="ClubBundle\Repository\ClubRepository")
 */
class Club
{
    /**
     * @var int
     *
     * @ORM\Column(name="idclub", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idclub;

    /**
     * @var string
     *
     * @ORM\Column(name="nomclub", type="string", length=255)
     */
    private $nomclub;

    /**
     * @ORM\Column(type="integer")
     */
    private $cap;

    /**
     * @return mixed
     */
    public function getCap()
    {
        return $this->cap;
    }

    /**
     * @param mixed $cap
     */
    public function setCap($cap)
    {
        $this->cap = $cap;
    }


    /**
     * Get idclub
     *
     * @return int
     */
    public function getIdclub()
    {
        return $this->idclub;
    }

    /**
     * Set nomclub
     *
     * @param string $nomclub
     *
     * @return Club
     */
    public function setNomclub($nomclub)
    {
        $this->nomclub = $nomclub;

        return $this;
    }

    /**
     * Get nomclub
     *
     * @return string
     */
    public function getNomclub()
    {
        return $this->nomclub;
    }

}

