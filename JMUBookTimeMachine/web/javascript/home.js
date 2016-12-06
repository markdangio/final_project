/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function moveForward()
{

    if (document.getElementById('sellClassName').value == null || document.getElementById('sellClassName').value == '' ||
            document.getElementById('sellClassSubject').value == null || document.getElementById('sellClassSubject').value == '' ||
            document.getElementById('sellClassNumber').value == null || document.getElementById('sellClassNumber').value == '' ||
            document.getElementById('sellClassSection').value == null || document.getElementById('sellClassSection').value == '' ||
            document.getElementById('sellClassProfessor').value == null || document.getElementById('sellClassProfessor').value == '')
    {
        console.log("no value");
    }
    else 
    {
        $("#myCarousel").carousel("next");
    }
}
function moveBackward()
{
    $("#myCarousel").carousel("prev");
}
