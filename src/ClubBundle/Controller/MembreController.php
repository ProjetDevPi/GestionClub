<?php

namespace ClubBundle\Controller;
use ClubBundle\Entity\User;
use ClubBundle\Entity\Club;
use ClubBundle\Entity\Membre;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

/**
 * Membre controller.
 *
 * @Route("membre")
 */
class MembreController extends Controller
{
    /**
     * Lists all membre entities.
     *
     * @Route("/", name="membre_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $membres = $em->getRepository('ClubBundle:Membre')->findAll();

        return $this->render('@Club/membre/index.html.twig', array(
            'membres' => $membres,
        ));
    }

    /**
     * Creates a new membre entity.
     *
     * @Route("/new", name="membre_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $membre = new Membre();
        $form = $this->createForm('ClubBundle\Form\MembreType', $membre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($membre);
            $em->flush();

            return $this->redirectToRoute('membre_show', array('id' => $membre->getId()));
        }

        return $this->render('@Cub/membre/new.html.twig', array(
            'membre' => $membre,
            'form' => $form->createView(),
        ));
    }


    /**
     * Finds and displays a membre entity.
     *
     * @Route("/{id}", name="membre_show")
     * @Method("GET")
     */
    public function showAction(Membre $membre)
    {
        $deleteForm = $this->createDeleteForm($membre);

        return $this->render('@Club/membre/show.html.twig', array(
            'membre' => $membre,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing membre entity.
     *
     * @Route("/{id}/edit", name="membre_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Membre $membre)
    {
        $deleteForm = $this->createDeleteForm($membre);
        $editForm = $this->createForm('ClubBundle\Form\MembreType', $membre);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('membre_edit', array('id' => $membre->getId()));
        }

        return $this->render('@Club/membre/edit.html.twig', array(
            'membre' => $membre,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }
/**
* @Route("/Membre/{idclub}", name="membre")
*/
    public function MembreAction($idclub)
    {
        print 'ihkhkjh';
        $user = $this->getUser();
        $u = $this->getDoctrine()->getRepository(User::class)->find($user);
        $com = new Membre();
        $club = $this->getDoctrine()->getRepository(Club::class)->find($idclub);
        $com->setIdclub($club);
        $com->setIdUser($u);

        $em = $this->getDoctrine()->getManager();
        $em->persist($com);

        $this->getDoctrine()->getRepository(membre::class )->
        changeCapacite($idclub);
        $em->flush();

        return $this->redirectToRoute('clubfront_index');
    }

    /**
     * Deletes a membre entity.
     *
     * @Route("/{id}", name="membre_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, Membre $membre)
    {
        $form = $this->createDeleteForm($membre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($membre);
            $em->flush();
        }

        return $this->redirectToRoute('membre_index');
    }

    /**
     * Creates a form to delete a membre entity.
     *
     * @param Membre $membre The membre entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Membre $membre)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('membre_delete', array('id' => $membre->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
