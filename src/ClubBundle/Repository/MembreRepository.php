<?php
/**
 * Created by PhpStorm.
 * User: User
 * Date: 19/02/2020
 * Time: 01:33
 */

namespace ClubBundle\Repository;
use ClubBundle\Entity\Club;
use ClubBundle\Entity\Membre;

class MembreRepository extends \Doctrine\ORM\EntityRepository
{
    public function changeCapacite($idclub)
    {
        $query=$this->getEntityManager()
            ->createQuery("update ClubBundle:Club j set j.cap=j.cap-1 WHERE j.idclub = '$idclub'");
        return $query->getResult();
    }

   // public function forgetMembre($idclub)
   // {
     //   $query=$this->getEntityManager()
      //      ->createQuery("update ClubBundle:Club j set j.cap=j.cap+1 WHERE j.idclub = '$idclub'");
     //   return $query->getResult();
   // }
}