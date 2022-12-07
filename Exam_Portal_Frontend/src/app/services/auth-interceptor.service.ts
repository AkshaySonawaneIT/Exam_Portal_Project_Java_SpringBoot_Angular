import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthServiceService } from "./auth-service/auth-service.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private service : AuthServiceService){}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // throw new Error("Method not implemented.");
        // let authreq = req;
        const token = this.service.getTokenFromLocalStorage();
        console.log("Inside interceptor...");
        if(token != null){
            req = req.clone({
                setHeaders:{Authorization: `Bearer ${token}`}
            });
        }
        console.log(req);
        return next.handle(req);
    }
}

// export const authInterceptorProviders = [
//     ,
// ];