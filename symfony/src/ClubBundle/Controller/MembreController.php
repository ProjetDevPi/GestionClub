<?php

namespace ClubBundle\Controller;
use ClubBundle\Entity\User;
use ClubBundle\Entity\Club;
use ClubBundle\Entity\Membre;
use ClubBundle\Entity\eleve;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use ClubBundle\Form\MembreType;

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
        $em = $this->getDoctrine()->getManager();

        $membres = $em->getRepository('ClubBundle:Membre')->findAll();

        return $this->render('@Club/membre/new.html.twig', array(
            'membres' => $membres,
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
    public function MembreAction(Request $request, $idclub)
    {
        $user = $this->getUser();
        $u = $this->getDoctrine()->getRepository(User::class)->find($user);
        $com = new Membre();
        $club = $this->getDoctrine()->getRepository(Club::class)->find($idclub);
        $form = $this->createForm('ClubBundle\Form\MembreType', $com);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $com->setIdclub($club);
            $com->setIdUser($u);


            $em->persist($com);
            $this->getDoctrine()->getRepository(membre::class)->
            changeCapacite($idclub);
            $em->flush();

            return $this->redirectToRoute('clubfront_index', array('id' => $com->getId()));
        }


        return $this->render('@Club/membre/new.html.twig', array(
            'com' => $com,
            'form' => $form->createView(),
        ));
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
            ->getForm();
    }


    /**
     * Creates a new membre entity.
     *
     * @Route("/new2", name="new2")
     * @Method({"GET", "POST"})
     */

    public function new2Action(Request $request, $idclub)
    {      $user = $this->getUser();
        $membre = new Membre();

        $club = $this->getDoctrine()->getRepository(Club::class)->find($idclub);

        $form = $this->createForm('ClubBundle\Form\MembreType', $membre);
        $form->handleRequest($request);


        $eleves= $this->getDoctrine()->getRepository(eleve::class)->findBy(array('user'=>$user));


        if ($form->isSubmitted() && $form->isValid()) {
            $us= $request->get('karim');
            $elev= $this->getDoctrine()->getRepository(eleve::class)->findOneBy(array('nom'=>$us));
            $membre->setIdUser($user);
            $em = $this->getDoctrine()->getManager();
            $membre->setIdclub($club);
            $membre->setEleve($elev);



            $em->persist($membre);
            $this->getDoctrine()->getRepository(membre::class)->
            changeCapacite($idclub);
            $em->flush();

            return $this->redirectToRoute('clubfront_index', array('id' => $membre->getId()));
        }


        # return $this->redirectToRoute('membre_show', array('id' => $membre->getId()));


        return $this->render('@Club/membre/new2.html.twig', array(
            'eleves' =>$eleves,
            'membre' => $membre,
            'form' => $form->createView(),


        ));
    }



    /**
     * Finds and displays a membre entity.
     *
     * @Route("membre_show/{id}", name="membre_show")
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
     *
     * @Route("/indexfront", name="membrefront_index")
     * @Method("GET")
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function indexfrontAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $membres = $em->getRepository('ClubBundle:Membre')->findAll();

        return $this->render('@Club/membre/indexfront.html.twig', array(
            'membres' => $membres,
        ));
    }

    /**
     *
     * @Route("/mail/{id}", name="mail")
     */
    public function mailAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $membres = $em->getRepository('ClubBundle:Membre')->find($id);

        $message = (new \Swift_Message('Acceptation dans un club'))
            ->setFrom('karim.bentarjem.1@esprit.tn')
            ->setTo('karim.bentarjem.1@esprit.tn')
            ->setBody(
                $this->renderView(
                // app/Resources/views/Emails/registration.html.twig
                    '@Club/membre/mail.html.twig', array(
                        'membres' => $membres,
                    )
                ),
                'text/html'
            );
        $this->get('mailer')->send($message);


        // or, you can also fetch the mailer service this way
        // $this->get('mailer')->send($message);
        return $this->redirectToRoute('membre_new');
    }

}
