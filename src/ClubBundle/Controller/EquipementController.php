<?php

namespace ClubBundle\Controller;

use ClubBundle\Entity\Equipement;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

/**
 * Equipement controller.
 *
 * @Route("equipement")
 */
class EquipementController extends Controller
{
    /**
     * Lists all equipement entities.
     *
     * @Route("/", name="equipement_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $equipements = $em->getRepository('ClubBundle:Equipement')->findAll();

        return $this->render('@Club/equipement/index.html.twig', array(
            'equipements' => $equipements,
        ));
    }

    /**
     * Creates a new equipement entity.
     *
     * @Route("/new", name="equipement_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $equipement = new Equipement();
        $form = $this->createForm('ClubBundle\Form\EquipementType', $equipement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($equipement);
            $em->flush();

            return $this->redirectToRoute('equipement_show', array('ideq' => $equipement->getIdeq()));
        }

        return $this->render('@Club/equipement/new.html.twig', array(
            'equipement' => $equipement,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a equipement entity.
     *
     * @Route("/{ideq}", name="equipement_show")
     * @Method("GET")
     */
    public function showAction(Equipement $equipement)
    {
        $deleteForm = $this->createDeleteForm($equipement);

        return $this->render('@Club/equipement/show.html.twig', array(
            'equipement' => $equipement,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing equipement entity.
     *
     * @Route("/{ideq}/edit", name="equipement_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Equipement $equipement)
    {
        $deleteForm = $this->createDeleteForm($equipement);
        $editForm = $this->createForm('ClubBundle\Form\EquipementType', $equipement);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('equipement_edit', array('ideq' => $equipement->getIdeq()));
        }

        return $this->render('@Club/equipement/edit.html.twig', array(
            'equipement' => $equipement,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a equipement entity.
     *
     * @Route("/{ideq}", name="equipement_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, Equipement $equipement)
    {
        $form = $this->createDeleteForm($equipement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($equipement);
            $em->flush();
        }

        return $this->redirectToRoute('equipement_index');
    }

    /**
     * Creates a form to delete a equipement entity.
     *
     * @param Equipement $equipement The equipement entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Equipement $equipement)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('equipement_delete', array('ideq' => $equipement->getIdeq())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
