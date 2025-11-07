import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private baseUrl = environment.baseUrl;

  constructor(protected http: HttpClient) { }

  // ✅ Créer un client (JSON pur)
  createClient(client: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/clients`, client);
  }

  // ✅ Mettre à jour un client
  updateClient(clientId: any, client: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/clients/${clientId}`, client, {
      observe: 'response'
    });
  }

  // ✅ Récupérer tous les clients
  getAllClients(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/clients/all`);
  }

  // ✅ Supprimer un client
  deleteClient(clientId: any): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/clients/${clientId}`, {
      observe: 'response'
    });
  }
}
