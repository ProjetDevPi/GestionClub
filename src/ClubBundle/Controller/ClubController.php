<?php

namespace ClubBundle\Controller;

use ClubBundle\Entity\Club;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

/**
 * Club controller.
 *
 * @Route("club")
 */
class ClubController extends Controller
{
    /**
     * Lists all club entities.
     *
     * @Route("/", name="club_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $clubs = $em->getRepository('ClubBundle:Club')->findAll();

        return $this->render('@Club/club/index.html.twig', array(
            'clubs' => $clubs,
        ));
    }

    /**
     *
     * @Route("/indexfront", name="clubfront_index")
     * @Method("GET")
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function indexfrontAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();

        $clubs = $em->getRepository('ClubBundle:Club')->findAll();

        if($request->getMethod() == Request::METHOD_GET) {
            $name = $request->get('filter');
            $clubs = $this->getDoctrine()->getRepository(Club::class)->mefind($name);
        }

        $query=$clubs;
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result=$paginator->paginate(
            $query,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',4)
        );

        return $this->render('@Club/club/indexfront.html.twig', array(
            'clubs' => $result,
        ));
    }

    /**
     * Creates a new club entity.
     *
     * @Route("/new", name="club_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $club = new Club();
        $form = $this->createForm('ClubBundle\Form\ClubType', $club);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($club);
            $em->flush();

            return $this->redirectToRoute('club_show', array('idclub' => $club->getIdclub()));
        }

        return $this->render('@Club/club/new.html.twig', array(
            'club' => $club,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a club entity.
     *
     * @Route("/{idclub}", name="club_show")
     * @Method("GET")
     */
    public function showAction(Club $club)
    {
        $deleteForm = $this->createDeleteForm($club);

        return $this->render('@Club/club/show.html.twig', array(
            'club' => $club,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing club entity.
     *
     * @Route("/{idclub}/edit", name="club_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Club $club)
    {
        $deleteForm = $this->createDeleteForm($club);
        $editForm = $this->createForm('ClubBundle\Form\ClubType', $club);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('club_edit', array('idclub' => $club->getIdclub()));
        }

        return $this->render('@Club/club/edit.html.twig', array(
            'club' => $club,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a club entity.
     *
     * @Route("/{idclub}", name="club_delete")
     * @Method("DELETE")
     */
    public function deleteAction($idclub)
    {
        $em = $this->getDoctrine()->getManager();
        $club = $em->getRepository(club::class)->find($idclub);

        $em->remove($club);
        $em->flush();
        return $this->redirectToRoute('club_index');
    }


    /**
     * Creates a form to delete a club entity.
     *
     * @param Club $club The club entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Club $club)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('club_delete', array('idclub' => $club->getIdclub())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }



}
