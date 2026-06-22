import { Injectable } from '@angular/core';
import jsPDF from 'jspdf';

export interface SearchResult {
  cardBrand: string;
  title: string;
  excerpt: string;
  sourceUrl: string;
  section: string;
}

@Injectable({
  providedIn: 'root'
})
export class PdfService {
  generateSearchResultsPDF(query: string, results: SearchResult[]): void {
    const doc = new jsPDF();
    
    let yPosition = 20;
    const pageHeight = doc.internal.pageSize.height;
    const margin = 20;
    const lineHeight = 10;

    doc.setFontSize(20);
    doc.text('Guia das Bandeiras - Resultados de Busca', margin, yPosition);
    yPosition += lineHeight * 2;

    doc.setFontSize(14);
    doc.text(`Termo pesquisado: ${query}`, margin, yPosition);
    yPosition += lineHeight * 2;

    doc.setFontSize(12);
    doc.text(`Total de resultados: ${results.length}`, margin, yPosition);
    yPosition += lineHeight * 2;

    doc.line(margin, yPosition, 210 - margin, yPosition);
    yPosition += lineHeight;

    results.forEach((result, index) => {
      if (yPosition > pageHeight - 40) {
        doc.addPage();
        yPosition = 20;
      }

      doc.setFontSize(14);
      doc.setFont('helvetica', 'bold');
      doc.text(`${index + 1}. ${result.title}`, margin, yPosition);
      yPosition += lineHeight;

      doc.setFontSize(10);
      doc.setFont('helvetica', 'normal');
      doc.text(`Bandeira: ${result.cardBrand} | Seção: ${result.section}`, margin, yPosition);
      yPosition += lineHeight;

      doc.setFontSize(10);
      const lines = doc.splitTextToSize(result.excerpt, 170);
      doc.text(lines, margin, yPosition);
      yPosition += lines.length * lineHeight;

      doc.setFontSize(9);
      doc.setTextColor(0, 0, 255);
      doc.text(`Fonte: ${result.sourceUrl}`, margin, yPosition);
      yPosition += lineHeight * 2;

      doc.setTextColor(0, 0, 0);
      doc.line(margin, yPosition, 210 - margin, yPosition);
      yPosition += lineHeight;
    });

    doc.setFontSize(8);
    doc.text(`Gerado em: ${new Date().toLocaleString('pt-BR')}`, margin, pageHeight - 10);

    doc.save(`guia-bandeiras-${query.replace(/\s+/g, '-')}.pdf`);
  }
}
