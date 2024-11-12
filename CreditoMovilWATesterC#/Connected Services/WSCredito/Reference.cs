﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Este código fue generado por una herramienta.
//     Versión de runtime:4.0.30319.42000
//
//     Los cambios en este archivo podrían causar un comportamiento incorrecto y se perderán si
//     se vuelve a generar el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace CreditoMovilWATesterC_.WSCredito {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", ConfigurationName="WSCredito.CreditoWS")]
    public interface CreditoWS {
        
        // CODEGEN: El parámetro 'credito' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/CreditoWS/insertarCreditoRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/CreditoWS/insertarCreditoResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        CreditoMovilWATesterC_.WSCredito.insertarCreditoResponse insertarCredito(CreditoMovilWATesterC_.WSCredito.insertarCreditoRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/CreditoWS/insertarCreditoRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/CreditoWS/insertarCreditoResponse")]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.insertarCreditoResponse> insertarCreditoAsync(CreditoMovilWATesterC_.WSCredito.insertarCreditoRequest request);
        
        // CODEGEN: El parámetro 'return' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/CreditoWS/listarCreditosRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/CreditoWS/listarCreditosResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        CreditoMovilWATesterC_.WSCredito.listarCreditosResponse listarCreditos(CreditoMovilWATesterC_.WSCredito.listarCreditosRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/CreditoWS/listarCreditosRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/CreditoWS/listarCreditosResponse")]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.listarCreditosResponse> listarCreditosAsync(CreditoMovilWATesterC_.WSCredito.listarCreditosRequest request);
        
        // CODEGEN: El parámetro 'numCredito' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/CreditoWS/eliminarCreditoRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/CreditoWS/eliminarCreditoResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        CreditoMovilWATesterC_.WSCredito.eliminarCreditoResponse eliminarCredito(CreditoMovilWATesterC_.WSCredito.eliminarCreditoRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/CreditoWS/eliminarCreditoRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/CreditoWS/eliminarCreditoResponse")]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.eliminarCreditoResponse> eliminarCreditoAsync(CreditoMovilWATesterC_.WSCredito.eliminarCreditoRequest request);
        
        // CODEGEN: El parámetro 'return' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/CreditoWS/obtenerCreditoPorIdRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/CreditoWS/obtenerCreditoPorIdResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdResponse obtenerCreditoPorId(CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/CreditoWS/obtenerCreditoPorIdRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/CreditoWS/obtenerCreditoPorIdResponse")]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdResponse> obtenerCreditoPorIdAsync(CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdRequest request);
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.8.9037.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="https://services.creditomovil.pucp.edu.pe")]
    public partial class credito : object, System.ComponentModel.INotifyPropertyChanged {
        
        private usuario clienteField;
        
        private string estadoField;
        
        private System.DateTime fechaOtorgamientoField;
        
        private bool fechaOtorgamientoFieldSpecified;
        
        private double montoField;
        
        private string numCreditoField;
        
        private int numCuotasField;
        
        private double tasaInteresField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public usuario cliente {
            get {
                return this.clienteField;
            }
            set {
                this.clienteField = value;
                this.RaisePropertyChanged("cliente");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public string estado {
            get {
                return this.estadoField;
            }
            set {
                this.estadoField = value;
                this.RaisePropertyChanged("estado");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public System.DateTime fechaOtorgamiento {
            get {
                return this.fechaOtorgamientoField;
            }
            set {
                this.fechaOtorgamientoField = value;
                this.RaisePropertyChanged("fechaOtorgamiento");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool fechaOtorgamientoSpecified {
            get {
                return this.fechaOtorgamientoFieldSpecified;
            }
            set {
                this.fechaOtorgamientoFieldSpecified = value;
                this.RaisePropertyChanged("fechaOtorgamientoSpecified");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
        public double monto {
            get {
                return this.montoField;
            }
            set {
                this.montoField = value;
                this.RaisePropertyChanged("monto");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=4)]
        public string numCredito {
            get {
                return this.numCreditoField;
            }
            set {
                this.numCreditoField = value;
                this.RaisePropertyChanged("numCredito");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=5)]
        public int numCuotas {
            get {
                return this.numCuotasField;
            }
            set {
                this.numCuotasField = value;
                this.RaisePropertyChanged("numCuotas");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=6)]
        public double tasaInteres {
            get {
                return this.tasaInteresField;
            }
            set {
                this.tasaInteresField = value;
                this.RaisePropertyChanged("tasaInteres");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.8.9037.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="https://services.creditomovil.pucp.edu.pe")]
    public abstract partial class usuario : object, System.ComponentModel.INotifyPropertyChanged {
        
        private bool activoField;
        
        private string apMaternoField;
        
        private string apPaternoField;
        
        private string contrasenhaField;
        
        private System.DateTime fechaField;
        
        private bool fechaFieldSpecified;
        
        private System.DateTime fechaVencimientoField;
        
        private bool fechaVencimientoFieldSpecified;
        
        private int idUsuarioField;
        
        private string nombreField;
        
        private System.DateTime ultimoLogueoField;
        
        private bool ultimoLogueoFieldSpecified;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public bool activo {
            get {
                return this.activoField;
            }
            set {
                this.activoField = value;
                this.RaisePropertyChanged("activo");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public string apMaterno {
            get {
                return this.apMaternoField;
            }
            set {
                this.apMaternoField = value;
                this.RaisePropertyChanged("apMaterno");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public string apPaterno {
            get {
                return this.apPaternoField;
            }
            set {
                this.apPaternoField = value;
                this.RaisePropertyChanged("apPaterno");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
        public string contrasenha {
            get {
                return this.contrasenhaField;
            }
            set {
                this.contrasenhaField = value;
                this.RaisePropertyChanged("contrasenha");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=4)]
        public System.DateTime fecha {
            get {
                return this.fechaField;
            }
            set {
                this.fechaField = value;
                this.RaisePropertyChanged("fecha");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool fechaSpecified {
            get {
                return this.fechaFieldSpecified;
            }
            set {
                this.fechaFieldSpecified = value;
                this.RaisePropertyChanged("fechaSpecified");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=5)]
        public System.DateTime fechaVencimiento {
            get {
                return this.fechaVencimientoField;
            }
            set {
                this.fechaVencimientoField = value;
                this.RaisePropertyChanged("fechaVencimiento");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool fechaVencimientoSpecified {
            get {
                return this.fechaVencimientoFieldSpecified;
            }
            set {
                this.fechaVencimientoFieldSpecified = value;
                this.RaisePropertyChanged("fechaVencimientoSpecified");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=6)]
        public int idUsuario {
            get {
                return this.idUsuarioField;
            }
            set {
                this.idUsuarioField = value;
                this.RaisePropertyChanged("idUsuario");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=7)]
        public string nombre {
            get {
                return this.nombreField;
            }
            set {
                this.nombreField = value;
                this.RaisePropertyChanged("nombre");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=8)]
        public System.DateTime ultimoLogueo {
            get {
                return this.ultimoLogueoField;
            }
            set {
                this.ultimoLogueoField = value;
                this.RaisePropertyChanged("ultimoLogueo");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool ultimoLogueoSpecified {
            get {
                return this.ultimoLogueoFieldSpecified;
            }
            set {
                this.ultimoLogueoFieldSpecified = value;
                this.RaisePropertyChanged("ultimoLogueoSpecified");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="insertarCredito", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class insertarCreditoRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public CreditoMovilWATesterC_.WSCredito.credito credito;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=1)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string codigoCliente;
        
        public insertarCreditoRequest() {
        }
        
        public insertarCreditoRequest(CreditoMovilWATesterC_.WSCredito.credito credito, string codigoCliente) {
            this.credito = credito;
            this.codigoCliente = codigoCliente;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="insertarCreditoResponse", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class insertarCreditoResponse {
        
        public insertarCreditoResponse() {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="listarCreditos", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class listarCreditosRequest {
        
        public listarCreditosRequest() {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="listarCreditosResponse", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class listarCreditosResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public CreditoMovilWATesterC_.WSCredito.credito[] @return;
        
        public listarCreditosResponse() {
        }
        
        public listarCreditosResponse(CreditoMovilWATesterC_.WSCredito.credito[] @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="eliminarCredito", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class eliminarCreditoRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string numCredito;
        
        public eliminarCreditoRequest() {
        }
        
        public eliminarCreditoRequest(string numCredito) {
            this.numCredito = numCredito;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="eliminarCreditoResponse", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class eliminarCreditoResponse {
        
        public eliminarCreditoResponse() {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="obtenerCreditoPorId", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class obtenerCreditoPorIdRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string numCredito;
        
        public obtenerCreditoPorIdRequest() {
        }
        
        public obtenerCreditoPorIdRequest(string numCredito) {
            this.numCredito = numCredito;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="obtenerCreditoPorIdResponse", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class obtenerCreditoPorIdResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public CreditoMovilWATesterC_.WSCredito.credito @return;
        
        public obtenerCreditoPorIdResponse() {
        }
        
        public obtenerCreditoPorIdResponse(CreditoMovilWATesterC_.WSCredito.credito @return) {
            this.@return = @return;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface CreditoWSChannel : CreditoMovilWATesterC_.WSCredito.CreditoWS, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class CreditoWSClient : System.ServiceModel.ClientBase<CreditoMovilWATesterC_.WSCredito.CreditoWS>, CreditoMovilWATesterC_.WSCredito.CreditoWS {
        
        public CreditoWSClient() {
        }
        
        public CreditoWSClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public CreditoWSClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public CreditoWSClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public CreditoWSClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        CreditoMovilWATesterC_.WSCredito.insertarCreditoResponse CreditoMovilWATesterC_.WSCredito.CreditoWS.insertarCredito(CreditoMovilWATesterC_.WSCredito.insertarCreditoRequest request) {
            return base.Channel.insertarCredito(request);
        }
        
        public void insertarCredito(CreditoMovilWATesterC_.WSCredito.credito credito, string codigoCliente) {
            CreditoMovilWATesterC_.WSCredito.insertarCreditoRequest inValue = new CreditoMovilWATesterC_.WSCredito.insertarCreditoRequest();
            inValue.credito = credito;
            inValue.codigoCliente = codigoCliente;
            CreditoMovilWATesterC_.WSCredito.insertarCreditoResponse retVal = ((CreditoMovilWATesterC_.WSCredito.CreditoWS)(this)).insertarCredito(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.insertarCreditoResponse> CreditoMovilWATesterC_.WSCredito.CreditoWS.insertarCreditoAsync(CreditoMovilWATesterC_.WSCredito.insertarCreditoRequest request) {
            return base.Channel.insertarCreditoAsync(request);
        }
        
        public System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.insertarCreditoResponse> insertarCreditoAsync(CreditoMovilWATesterC_.WSCredito.credito credito, string codigoCliente) {
            CreditoMovilWATesterC_.WSCredito.insertarCreditoRequest inValue = new CreditoMovilWATesterC_.WSCredito.insertarCreditoRequest();
            inValue.credito = credito;
            inValue.codigoCliente = codigoCliente;
            return ((CreditoMovilWATesterC_.WSCredito.CreditoWS)(this)).insertarCreditoAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        CreditoMovilWATesterC_.WSCredito.listarCreditosResponse CreditoMovilWATesterC_.WSCredito.CreditoWS.listarCreditos(CreditoMovilWATesterC_.WSCredito.listarCreditosRequest request) {
            return base.Channel.listarCreditos(request);
        }
        
        public CreditoMovilWATesterC_.WSCredito.credito[] listarCreditos() {
            CreditoMovilWATesterC_.WSCredito.listarCreditosRequest inValue = new CreditoMovilWATesterC_.WSCredito.listarCreditosRequest();
            CreditoMovilWATesterC_.WSCredito.listarCreditosResponse retVal = ((CreditoMovilWATesterC_.WSCredito.CreditoWS)(this)).listarCreditos(inValue);
            return retVal.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.listarCreditosResponse> CreditoMovilWATesterC_.WSCredito.CreditoWS.listarCreditosAsync(CreditoMovilWATesterC_.WSCredito.listarCreditosRequest request) {
            return base.Channel.listarCreditosAsync(request);
        }
        
        public System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.listarCreditosResponse> listarCreditosAsync() {
            CreditoMovilWATesterC_.WSCredito.listarCreditosRequest inValue = new CreditoMovilWATesterC_.WSCredito.listarCreditosRequest();
            return ((CreditoMovilWATesterC_.WSCredito.CreditoWS)(this)).listarCreditosAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        CreditoMovilWATesterC_.WSCredito.eliminarCreditoResponse CreditoMovilWATesterC_.WSCredito.CreditoWS.eliminarCredito(CreditoMovilWATesterC_.WSCredito.eliminarCreditoRequest request) {
            return base.Channel.eliminarCredito(request);
        }
        
        public void eliminarCredito(string numCredito) {
            CreditoMovilWATesterC_.WSCredito.eliminarCreditoRequest inValue = new CreditoMovilWATesterC_.WSCredito.eliminarCreditoRequest();
            inValue.numCredito = numCredito;
            CreditoMovilWATesterC_.WSCredito.eliminarCreditoResponse retVal = ((CreditoMovilWATesterC_.WSCredito.CreditoWS)(this)).eliminarCredito(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.eliminarCreditoResponse> CreditoMovilWATesterC_.WSCredito.CreditoWS.eliminarCreditoAsync(CreditoMovilWATesterC_.WSCredito.eliminarCreditoRequest request) {
            return base.Channel.eliminarCreditoAsync(request);
        }
        
        public System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.eliminarCreditoResponse> eliminarCreditoAsync(string numCredito) {
            CreditoMovilWATesterC_.WSCredito.eliminarCreditoRequest inValue = new CreditoMovilWATesterC_.WSCredito.eliminarCreditoRequest();
            inValue.numCredito = numCredito;
            return ((CreditoMovilWATesterC_.WSCredito.CreditoWS)(this)).eliminarCreditoAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdResponse CreditoMovilWATesterC_.WSCredito.CreditoWS.obtenerCreditoPorId(CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdRequest request) {
            return base.Channel.obtenerCreditoPorId(request);
        }
        
        public CreditoMovilWATesterC_.WSCredito.credito obtenerCreditoPorId(string numCredito) {
            CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdRequest inValue = new CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdRequest();
            inValue.numCredito = numCredito;
            CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdResponse retVal = ((CreditoMovilWATesterC_.WSCredito.CreditoWS)(this)).obtenerCreditoPorId(inValue);
            return retVal.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdResponse> CreditoMovilWATesterC_.WSCredito.CreditoWS.obtenerCreditoPorIdAsync(CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdRequest request) {
            return base.Channel.obtenerCreditoPorIdAsync(request);
        }
        
        public System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdResponse> obtenerCreditoPorIdAsync(string numCredito) {
            CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdRequest inValue = new CreditoMovilWATesterC_.WSCredito.obtenerCreditoPorIdRequest();
            inValue.numCredito = numCredito;
            return ((CreditoMovilWATesterC_.WSCredito.CreditoWS)(this)).obtenerCreditoPorIdAsync(inValue);
        }
    }
}