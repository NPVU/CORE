/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function verifyDelete(){
    if(confirm('Bạn có chắc chắn xóa dữ liệu này !')){
        return true;
    } else {
        return false;
    }
}


function closeModal(cla){
    $('.'+cla).modal('hide');
    $('.btnRenderAvatar').click();
}


function showImage(cla, sr){
    $('img.'+cla).attr('src',sr);
}