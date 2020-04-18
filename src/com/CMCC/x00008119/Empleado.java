package com.CMCC.x00008119;

import java.util.ArrayList;

public abstract class Empleado {
    protected String nombre;
    protected String puesto;
    protected ArrayList<Documento> documentos;
    protected double salario;

    public Empleado(String nombre, String puesto, double salario) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        documentos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }

/*
        public void addDocumento (Documento documento) throws AlreadyExistDocumentException {
            boolean documentoExistente = false;
            for (Documento doc : documentos) {
                if (doc.getNombre().equalsIgnoreCase(documento.getNombre())) ;
                documentoExistente = true;
            }
            if (documentoExistente)
                throw new AlreadyExistDocumentException("Ya existe un documento con ese nombre.");
            else
                documentos.add(documento);
        }

        public void removeDocumento (String documento) throws NotFoundDocumentException {
            boolean documentoEncontrado = false;
            for (Documento doc : documentos) {
                if (doc.getNombre().equalsIgnoreCase(documento))
                    documentoEncontrado = true;
            }
            if (documentoEncontrado)
                documentos.removeIf(s -> s.getNombre().equalsIgnoreCase(documento));
            else throw new NotFoundDocumentException("El documento no ha sido encontrado.\n");
        }
*/
        public double getSalario () {
            return salario;
        }

        public void setSalario ( double salario){
            this.salario = salario;
        }
    }
