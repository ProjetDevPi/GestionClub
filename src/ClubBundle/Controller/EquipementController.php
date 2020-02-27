<?php

namespace ClubBundle\Controller;
use Knp\Snappy\Pdf;
use ClubBundle\Entity\Club;
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
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $equipements = $em->getRepository('ClubBundle:Equipement')->findAll();

        $query=$equipements;
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result=$paginator->paginate(
            $query,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',4)
        );

        return $this->render('@Club/equipement/index.html.twig', array(
            'equipements' => $result,
        ));
    }

    /**
     *
     *
     * @Route("/pdf", name="equipement_pdf")
     * @Method("GET")
     */
    public function pdfAction()
    {
        $em = $this->getDoctrine()->getManager();

        $equipements = $em->getRepository('ClubBundle:Equipement')->findAll();
        $snappy = new Pdf('C:\tmp\wkhtmltox\bin\wkhtmltopdf');
        //$snappy->generateFromHtml('<h1>Bill</h1>', '/tmp/karim.pdf');
        $html="<style>

.clearfix:after {
  content: \"\";
  display: table;
  clear: both;
}

a {
  color: #001028;
  text-decoration: none;
}

body {
  font-family: Junge;
  position: relative;
  width: 21cm;  
  height: 29.7cm; 
  margin: 0 auto; 
  color: #001028;
  background: #FFFFFF; 
  font-size: 14px; 
}

.arrow {
  margin-bottom: 4px;
}

.arrow.back {
  text-align: right;
}

.inner-arrow {
  padding-right: 10px;
  height: 30px;
  display: inline-block;
  background-color: rgb(233, 125, 49);
  text-align: center;

  line-height: 30px;
  vertical-align: middle;
}

.arrow.back .inner-arrow {
  background-color: rgb(233, 217, 49);
  padding-right: 0;
  padding-left: 10px;
}

.arrow:before,
.arrow:after {
  content:'';
  display: inline-block;
  width: 0; height: 0;
  border: 15px solid transparent;
  vertical-align: middle;
}

.arrow:before {
  border-top-color: rgb(233, 125, 49);
  border-bottom-color: rgb(233, 125, 49);
  border-right-color: rgb(233, 125, 49);
}

.arrow.back:before {
  border-top-color: transparent;
  border-bottom-color: transparent;
  border-right-color: rgb(233, 217, 49);
  border-left-color: transparent;
}

.arrow:after {
  border-left-color: rgb(233, 125, 49);
}

.arrow.back:after {
  border-left-color: rgb(233, 217, 49);
  border-top-color: rgb(233, 217, 49);
  border-bottom-color: rgb(233, 217, 49);
  border-right-color: transparent;
}

.arrow span { 
  display: inline-block;
  width: 80px; 
  margin-right: 20px;
  text-align: right; 
}

.arrow.back span { 
  margin-right: 0;
  margin-left: 20px;
  text-align: left; 
}

h1 {
  color: #5D6975;
  font-family: Junge;
  font-size: 2.4em;
  line-height: 1.4em;
  font-weight: normal;
  text-align: center;
  border-top: 1px solid #5D6975;
  border-bottom: 1px solid #5D6975;
  margin: 0 0 2em 0;
}

h1 small { 
  font-size: 0.45em;
  line-height: 1.5em;
  float: left;
} 

h1 small:last-child { 
  float: right;
} 

#project { 
  float: left; 
}

#company { 
  float: right; 
}

table {
  width: 100%;
  border-collapse: collapse;
  border-spacing: 0;
  margin-bottom: 30px;
}

table th,
table td {
  text-align: center;
}

table th {
  padding: 5px 20px;
  color: #5D6975;
  border-bottom: 1px solid #C1CED9;
  white-space: nowrap;        
  font-weight: normal;
}

table .service,
table .desc {
  text-align: left;
}

table td {
  padding: 20px;
  text-align: right;
}

table td.service,
table td.desc {
  vertical-align: top;
}

table td.unit,
table td.qty,
table td.total {
  font-size: 1.2em;
}

table td.sub {
  border-top: 1px solid #C1CED9;
}

table td.grand {
  border-top: 1px solid #5D6975;
}

table tr:nth-child(2n-1) td {
  background: #EEEEEE;
}

table tr:last-child td {
  background: #DDDDDD;
}

#details {
  margin-bottom: 30px;
}

footer {
  color: #5D6975;
  width: 100%;
  height: 30px;
  position: absolute;
  bottom: 0;
  border-top: 1px solid #C1CED9;
  padding: 8px 0;
  text-align: center;
}</style>
<h1>Liste Les Equipements</h1>
 <!DOCTYPE html>
<html lang=\"en\">
  <head>
    <meta charset=\"utf-8\">
    <title>Example 3</title>
    <link rel=\"stylesheet\" href=\"style.css\" media=\"all\" />
  </head>
  <body>
    <main>
      <table>
        <thead>
          <tr><th class=\"service\">NOM DES EQUIPEMENTS</th>
            <th class=\"desc\"ID</th>
            <th>TYPE</th>
          </tr>
        </thead><tbody>
";
        foreach ($equipements as $e){
            $html=$html."<tr>
            <td class=\"service\">". $e->getNom()."</td>
            <td class=\"desc\">". $e->getIdeq()."</td>
            <td class=\"unit\">". $e->getType()."</td>
          </tr>";
        }
        $html=$html." </tbody>
      </table>
      <div id=\"details\" class=\"clearfix\">
        <div id=\"project\">
          <div class=\"arrow\"><div class=\"inner-arrow\"><span>GARDEN</span>KIDO'S</div></div>
          <div class=\"arrow\"><div class=\"inner-arrow\"><span>(216)20009000</span> PHONE</div></div>
          </div>
        <div id=\"company\">
          <div class=\"arrow back\"><div class=\"inner-arrow\">Ariana soghra<span>ADDRESSE</span></div></div>
          <div class=\"arrow back\"><div class=\"inner-arrow\">EMAIL<span>kidos@esprit.com</span></div></div>
           </div>
      </div>
      <div id=\"notices\">
        <div>NOTICE:</div>
        <div class=\"notice\">L'une des meilleures jardin d'enfant classer au monde.</div>
      </div>
    </main>
    <footer>
     Kido's
    </footer>
  </body>
</html>";
        $snappy->generateFromHtml($html, '/tmp/ListeDesEquipement.pdf');


        return $this->redirectToRoute('equipement_index');
    }

    /**
     * Lists all equipement entities.
     *
     * @Route("/{idclub}/indexfront", name="equipementfront_index")
     * @Method("GET")
     */
    public function indexfrontAction($idclub)
    {
        $em = $this->getDoctrine()->getManager();


        $equipement = $em->getRepository('ClubBundle:Equipement')->findBy(array('idclub'=>$idclub));
        return $this->render('@Club/equipement/indexfront.html.twig', array(
            'equipement' => $equipement,


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
