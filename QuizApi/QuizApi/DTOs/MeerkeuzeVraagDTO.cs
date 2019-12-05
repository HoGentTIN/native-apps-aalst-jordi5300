using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.DTOs
{
    public class MeerkeuzeVraagDTO
    {
        [Required]
        public string Vraag { get; set; }
        [Required]
        public string Keuze1 { get; set; }
        [Required]
        public string Keuze2 { get; set; }
        [Required]
        public string Keuze3 { get; set; }
        [Required]
        public string Antwoord { get; set; }
    }
}
