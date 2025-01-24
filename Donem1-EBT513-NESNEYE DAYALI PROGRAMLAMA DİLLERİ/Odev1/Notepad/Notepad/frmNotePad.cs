/****************************************************************************
**					SAKARYA ÜNİVERSİTESİ
**				BİLGİSAYAR VE BİLİŞİM BİLİMLERİ FAKÜLTESİ
**				    BİLGİSAYAR MÜHENDİSLİĞİ BÖLÜMÜ
**				   NESNEYE DAYALI PROGRAMLAMA DERSİ
**					2023-2024 BAHAR DÖNEMİ
**	
**				ÖDEV NUMARASI..........:1. Ödev
**				ÖĞRENCİ ADI............:Sedat Öztürk
**				ÖĞRENCİ NUMARASI.......:E235013168
**              DERSİN ALINDIĞI GRUP...:Uzaktan Eğitim A
****************************************************************************/

using System;
using System.IO;
using System.Windows.Forms;

namespace Notepad
{
    public partial class Notepad : Form
    {
        public string strFilePath { get; set; } //Açılan dosyanın path
        public bool blnModified { get; set; } //Richtextbox içerisinde bir değişiklik yapılmış mı ?

        private const string strTitle = "My NotePad";

        public Notepad()
        {
            InitializeComponent();
            strFilePath = "";
            blnModified = false;

            this.Text = strTitle;
        }

        //düzenleme seçenekleri
        enum Shortcut
        {
            Cut,
            Copy,
            Paste
        }

        //format seçenekleri
        enum Format
        {
            ForeColor,
            BackColor,
            Font,
            AlignmentLeft,
            AlignmentCenter,
            AlignmentRight
        }

        private void Notepad_Load(object sender, EventArgs e)
        {
            //notepad açıldığında maksimize açılsın
            this.WindowState = FormWindowState.Maximized;
        }

        private void Notepad_FormClosing(object sender, FormClosingEventArgs e)
        {
            //Notepad kapatılırken düzenleme yapılıp yapılmadığı kontrol edilir. 
            //Eğer düzenleme varsa uyarı çıkarıp kayıt etmek istediği sorulur.
            DegisiklikKaydedilsinmi();
        }

        #region Menu

        private void menuForeColor_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.ForeColor);
        }

        private void menuBackColor_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.BackColor);
        }

        private void menuFont_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.Font);
        }

        private void menuSol_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.AlignmentLeft);
        }

        private void menuOrta_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.AlignmentCenter);
        }

        private void menuSag_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.AlignmentRight);
        }

        private void menuYeni_Click(object sender, EventArgs e)
        {
            DegisiklikKaydedilsinmi();
            txtNotepad.Text = "";
            strFilePath = "";
            blnModified = false;
            SetTitle();
        }

        private void menuDosyaAc_Click(object sender, EventArgs e)
        {
            DegisiklikKaydedilsinmi();

            try
            {
                //Nptepad sadece rtf uzantılı dosyaları işler ve açar.
                FileDialog.DefaultExt = "*.rtf";
                FileDialog.Filter = "RTF Files|*.rtf";

                //Eğer dialog üzeirnden bir dosya seçildiyse içeriği richtextbox aktarılıyor.
                if (FileDialog.ShowDialog() == DialogResult.OK && FileDialog.FileName.Length > 0)
                {
                    txtNotepad.LoadFile(FileDialog.FileName);
                    strFilePath = FileDialog.FileName;
                    blnModified = false;
                    SetTitle();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void menuDosyaKaydet_Click(object sender, EventArgs e)
        {
            FileSave();
        }

        private void menuDosyaFarkliKaydet_Click(object sender, EventArgs e)
        {
            FileSave(true);
        }

        private void menuCikis_Click(object sender, EventArgs e)
        {
            DegisiklikKaydedilsinmi();

            this.Dispose();
        }

        private void menuCSharp_Click(object sender, EventArgs e)
        {
            DegisiklikKaydedilsinmi();

            DirectoryInfo programPath = new DirectoryInfo(Environment.CurrentDirectory);

            //C# için şablon dosyası açılıyor.
            //Bu şablon dosyaları projenin içerisin gömülmüştür.
            txtNotepad.LoadFile($"{programPath.FullName}\\Sablon\\cSharp.rtf");
        }

        private void menuCpp_Click(object sender, EventArgs e)
        {
            DegisiklikKaydedilsinmi();

            DirectoryInfo programPath = new DirectoryInfo(Environment.CurrentDirectory);

            //C++ için şablon dosyası açılıyor.
            //Bu şablon dosyaları projenin içerisin gömülmüştür.
            txtNotepad.LoadFile($"{programPath.FullName}\\Sablon\\cPP.rtf");
        }

        private void menuC_Click(object sender, EventArgs e)
        {
            DegisiklikKaydedilsinmi();

            DirectoryInfo programPath = new DirectoryInfo(Environment.CurrentDirectory);

            //C için şablon dosyası açılıyor.
            //Bu şablon dosyaları projenin içerisin gömülmüştür.
            txtNotepad.LoadFile($"{programPath.FullName}\\Sablon\\c.rtf");
        }
        #endregion

        //Herhangi bir yazının belli bir kısmını formatlayabiliriz. Format enumu sayesinde yapılacakların listesi;
        //Yazı rengi, arka plan rengi, font büyüklüğü ve tarzı, sağa sola ve ortala 
        private void FormattingText(int start, int len, Format format)
        {
            DialogResult result;
            txtNotepad.Select(start, len); //start ve len arasındaki yazı seçilir.

            switch (format)
            {
                case Format.ForeColor: //Yazı rengi
                    result = ColorDialog.ShowDialog();
                    if (result == DialogResult.OK)
                    {
                        txtNotepad.SelectionColor = ColorDialog.Color;
                    }
                    break;
                case Format.BackColor: //Arka plan rengi
                    result = ColorDialog.ShowDialog();
                    if (result == DialogResult.OK)
                    {
                        txtNotepad.SelectionBackColor = ColorDialog.Color;
                    }
                    break;
                case Format.Font: //Font büyüklüğü ve font çeşidi
                    result = FontDialog.ShowDialog();
                    if (result == DialogResult.OK)
                    {
                        txtNotepad.SelectionFont = FontDialog.Font;
                    }
                    break;
                case Format.AlignmentLeft: //Sola yasla
                    txtNotepad.SelectionAlignment = HorizontalAlignment.Left;
                    break;
                case Format.AlignmentCenter: //Ortala
                    txtNotepad.SelectionAlignment = HorizontalAlignment.Center;
                    break;
                case Format.AlignmentRight: //Sağa yasla
                    txtNotepad.SelectionAlignment = HorizontalAlignment.Right;
                    break;
                default:
                    break;
            }
        }

        #region Toolbox

        private void toolCut_Click(object sender, EventArgs e)
        {
            CutCopyPaste(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Shortcut.Cut);
        }

        private void toolCopy_Click(object sender, EventArgs e)
        {
            CutCopyPaste(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Shortcut.Copy);
        }

        private void toolPaste_Click(object sender, EventArgs e)
        {
            CutCopyPaste(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Shortcut.Paste);
        }

        private void toolForeColor_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.ForeColor);
        }

        private void toolBackColor_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.BackColor);
        }

        private void toolFont_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.Font);
        }

        private void toolLeft_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.AlignmentLeft);
        }

        private void toolCenter_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.AlignmentCenter);
        }

        private void toolRight_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.AlignmentRight);
        }

        #endregion

        //Herhangi bir yazının belli bir kısmını keser, kopyalar ve yapıştırabiliriz.
        //Düzenleme seçenekleri shortcut enum ile yönetilir.
        private void CutCopyPaste(int start, int len, Shortcut shortcut)
        {
            txtNotepad.Select(start, len); //start ve len arasındaki yazı seçilir.

            switch (shortcut)
            {
                case Shortcut.Cut: //Kes
                    if (txtNotepad.SelectedText != "")
                    {
                        Clipboard.SetText(txtNotepad.SelectedRtf, TextDataFormat.Rtf);
                        txtNotepad.SelectedText = "";
                    }
                    break;
                case Shortcut.Copy: //Kopyala
                    if (txtNotepad.SelectedText != "")
                    {
                        Clipboard.SetText(txtNotepad.SelectedRtf, TextDataFormat.Rtf);
                    }
                    break;
                case Shortcut.Paste: //Yapıştır
                    //Eğer Kopyalanan hazıfadaki yazı eğer RTF ise RTF formatında yapıştırır.
                    //Eğer kopyalanan hafızadaki yazı eğer Text ise Text formatında yapıştırır.
                    //Bunun sebebi RTF yi text olarak kopyalayınca istenmeyen karakterler açığıa çıkıyor.
                    if (Clipboard.ContainsText(TextDataFormat.Rtf))
                    {
                        txtNotepad.SelectedRtf = Clipboard.GetData(DataFormats.Rtf).ToString();
                    }
                    else if (Clipboard.ContainsText(TextDataFormat.Text))
                    {
                        txtNotepad.SelectedText = Clipboard.GetData(DataFormats.Text).ToString();
                    }

                    break;
                default:
                    break;
            }
        }

        #region ContextMenu

        private void contextItemCut_Click(object sender, EventArgs e)
        {
            CutCopyPaste(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Shortcut.Cut);
        }

        private void contextItemCopy_Click(object sender, EventArgs e)
        {
            CutCopyPaste(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Shortcut.Copy);
        }

        private void contextItemPaste_Click(object sender, EventArgs e)
        {
            CutCopyPaste(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Shortcut.Paste);
        }

        private void contextItemForeColor_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.ForeColor);
        }

        private void contextItemBackColor_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.BackColor);
        }

        private void contextItemFont_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.Font);
        }

        private void contextItemAlignLeft_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.AlignmentLeft);
        }

        private void contextItemAlignCenter_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.AlignmentCenter);
        }

        private void contextItemAlignRight_Click(object sender, EventArgs e)
        {
            FormattingText(txtNotepad.SelectionStart, txtNotepad.SelectionLength, Format.AlignmentRight);
        }

        #endregion

        //Eğer notepad üzerinden bir değişilik yapıldıysa global değişken olan blnModified değişkenine bu bilgi verilir.
        private void txtNotepad_TextChanged(object sender, EventArgs e)
        {
            txtNotepad.Modified = true;
            blnModified = true;
            SetTitle();
        }

        private void DegisiklikKaydedilsinmi()
        {
            //Eğer notepad üzerinden bir düzenleme yapıldıysa program bu değişikliği kaydetmek isteyip istemediğini sorar.
            if (blnModified)
            {
                if (MessageBox.Show("Yaptığınız değişiklikleri kaydetmek ister misiniz?", "KAYDET", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    FileSave();
                }
            }
        }

        private void FileSave(bool blnSaveAs = false)
        {
            //Eğer bir dosya açılmış ve henüz kaydedilmemişse otomatik kaydedilir.
            //Eğer yeni bir dosya üzerinde çalışılmış ve bir dosya ismi belirlenmişse o dosya ilgili yoldaki klasöre kaydedilir.
            if (strFilePath.Length > 0 && blnSaveAs == false)
            {
                txtNotepad.SaveFile(strFilePath, RichTextBoxStreamType.RichText);
                blnModified = false;
            }
            else
            {
                SaveDialog.DefaultExt = "*.rtf"; //dialog otomatik rtf dosyasını gösterir
                SaveDialog.Filter = "RTF Files|*.rtf"; 

                //Eğer dosya adı verilmiş ve OK basıldıysa dosya kaydedilir.
                if (SaveDialog.ShowDialog() == DialogResult.OK && SaveDialog.FileName.Length > 0)
                {
                    txtNotepad.SaveFile(SaveDialog.FileName, RichTextBoxStreamType.RichText);
                    strFilePath = SaveDialog.FileName;
                    blnModified = false;
                }
            }

            SetTitle();
        }

        //Notepad üzerinden çalışılan dosyaının yolu gösterilir.
        //Eğer dosyanın üzerinden bir düzenleme yapıldıysa * işaret ile gösterilir.
        private void SetTitle()
        {
            this.Text = strTitle + (String.IsNullOrEmpty(strFilePath) ? "" : " - " + strFilePath) + (blnModified ? " *" : "");
        }
    }
}
