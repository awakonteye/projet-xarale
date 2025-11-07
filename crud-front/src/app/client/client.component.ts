import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../service/client.service';

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent {

  clients: any[] = [];
  filteredClients: any[] = [];
  pagedClients: any[] = [];

  showModal: boolean = false;
  isEditMode: boolean = false;
  selectedClient: any = { name: '', email: '', phone: '', status: 'Actif' };

  searchTerm: string = '';

  // Pagination
  pageSize: number = 5;
  currentPage: number = 1;
  totalPages: number = 0;
  pages: number[] = [];

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.loadClients();
  }

  // Charger tous les clients
  loadClients(): void {
    this.clientService.getAllClients().subscribe({
      next: (res) => {
        this.clients = res.payload;
        this.filteredClients = [...this.clients];
        this.setupPagination();
      },
      error: (err) => {
        console.error('Erreur chargement clients', err);
        alert('Erreur lors du chargement des clients');
      }
    });
  }

  // Filtrer par nom ou ID
  filterClients(): void {
    const term = this.searchTerm.toLowerCase();
    this.filteredClients = this.clients.filter(c =>
      c.name.toLowerCase().includes(term) || String(c.id).includes(term)
    );
    this.currentPage = 1;
    this.setupPagination();
  }

  // Pagination
  setupPagination(): void {
    this.totalPages = Math.ceil(this.filteredClients.length / this.pageSize);
    this.pages = Array.from({ length: this.totalPages }, (_, i) => i + 1);
    this.updatePagedClients();
  }

  updatePagedClients(): void {
    const start = (this.currentPage - 1) * this.pageSize;
    this.pagedClients = this.filteredClients.slice(start, start + this.pageSize);
  }

  goToPage(page: number): void {
    this.currentPage = page;
    this.updatePagedClients();
  }

  prevPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePagedClients();
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.updatePagedClients();
    }
  }

  // Modals et CRUD (inchangés)
  openAddModal(): void {
    this.isEditMode = false;
    this.selectedClient = { name: '', email: '', phone: '', status: 'Actif' };
    this.showModal = true;
  }

  openEditModal(client: any): void {
    this.isEditMode = true;
    this.selectedClient = { ...client };
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  saveClient(): void {
    if (this.isEditMode && this.selectedClient.id) {
      this.clientService.updateClient(this.selectedClient.id, this.selectedClient).subscribe({
        next: () => {
          alert('Client modifié avec succès');
          this.loadClients();
          this.closeModal();
        },
        error: (err) => {
          console.error('Erreur modification client', err);
          alert('Erreur lors de la modification du client');
        }
      });
    } else {
      this.clientService.createClient(this.selectedClient).subscribe({
        next: () => {
          alert('Client ajouté avec succès');
          this.loadClients();
          this.closeModal();
        },
        error: (err) => {
          console.error('Erreur création client', err);
          alert('Erreur lors de la création du client');
        }
      });
    }
  }

  deleteClient(clientId: number): void {
    if (confirm('Voulez-vous vraiment supprimer ce client ?')) {
      this.clientService.deleteClient(clientId).subscribe({
        next: () => {
          alert('Client supprimé avec succès');
          this.loadClients();
        },
        error: (err) => {
          console.error('Erreur suppression client', err);
          alert('Erreur lors de la suppression du client');
        }
      });
    }
  }
}
