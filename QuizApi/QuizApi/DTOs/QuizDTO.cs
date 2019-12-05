using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.DTOs
{
    public class QuizDTO
    {
        [Required]
        public string Naam { get; set; }
        [Required]
        public string Categorie { get; set; }
        [Required]
        public ICollection<MeerkeuzeVraagDTO> Meerkeuzevragen { get; set; }
    }
}
