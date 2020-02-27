<?php

namespace ClubBundle\Repository;

/**
 * ClubRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class ClubRepository extends \Doctrine\ORM\EntityRepository
{
  /*  public function getClubs($idu){
        return $this->getEntityManager()->createQuery('select c ClubBundle\Entity\Club c where id_user <> :idu ')->getResult();
}
*/
    public function mefind($name){
        $query=$this->getEntityManager()
            ->createQuery("select c from ClubBundle:Club c where c.nomclub like :name ");
        $query->setParameter(":name",'%'.$name.'%');
        return $query->getResult();
    }
}
