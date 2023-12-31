import decode from 'jwt-decode';

export default class LoginController {

    static async userLogin(username,password){
        
        const url = `http://localhost:9090/api/authenticate?username=${username}&password=${password}`;

        return await fetch(url,{
            method: "GET",
        }).then(response => response.json()).then(data =>{
            localStorage.setItem("token",data.token);
            localStorage.setItem("username",username);
            return data.status;
        }).catch(err => {console.log(err); return false;});
        
        
    }

    //check token
    static isExpired(){
        var isExpired = false;
        const token = localStorage.getItem('token');
        if(token !== "" && token !== null ){
            var decodedToken=decode(token, {complete: true});
            var dateNow = new Date();
            if(decodedToken.exp < dateNow.getTime())
                isExpired = true;
        }
        return isExpired;
    }

    //logout 
    static logout(){
        localStorage.removeItem("token");
        window.location.href="login";
    }


}
